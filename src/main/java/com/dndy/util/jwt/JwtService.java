package com.dndy.util.jwt;


import com.alibaba.fastjson.JSONObject;
import com.dndy.model.MJwt;
import com.dndy.model.MLoginRedis;
import com.dndy.model.MSub;
import com.dndy.model.Secret;
import com.dndy.util.redis.RedisUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.HashMap;
import java.util.Map;

@Service("jwtService")
public class JwtService{


    @Autowired
    private RedisUtil redisUtil;

    protected JSONObject json = new JSONObject();

    //注入密钥
    @Autowired
    private Secret secret;

    //jwt有效天数
    private static final int JWT_TIME = 30;
    //jwt前缀
    private static final String JWT_PREF = "JWT_";

    /**
     * 验证
     * @return
     */
    public String authJwt(String jwt) {
        try {
            String jwtJson = parseJWT(jwt);

            MJwt claims = json.parseObject(jwtJson,MJwt.class);

            MSub mSub = json.parseObject(claims.getSub(),MSub.class);

            String keyId = mSub.getUid().toString();

            Object object = this.redisUtil.getKey("JWT_" + keyId);

            MLoginRedis mLoginRedis = json.parseObject(json.toJSONString(object),MLoginRedis.class);

            if (mLoginRedis.getUuid().equals(claims.getJti())) {
                //TODO 时间未定
                redisUtil.setExpire(JWT_PREF+keyId, JWT_TIME);

                Map<String,Object> re=new HashMap<>();

                re.put("uid",mSub.getUid());

                re.put("deviceToken",mLoginRedis.getDeviceToken());

                re.put("Platform",mLoginRedis.getPlatform());

                re.put("useType",mSub.getLoginType());

                return json.toJSONString(re);

            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }
    /**
     * 存储jwt到redis
     * @return
     */
    public void saveJwt(String jwt,Long uid,String uuid,String deviceToken,Integer Platform,String institutionId) {
        String keyId = "";
        keyId = uid.toString();
        redisUtil.remove(JWT_PREF+keyId);
        MLoginRedis mLoginRedis=new MLoginRedis(uuid,jwt,deviceToken,Platform,institutionId);
        redisUtil.setKey(JWT_PREF+keyId,mLoginRedis,JWT_TIME);
    }

    /**
     * 由字符串生成加密key
     * @return
     */
    public SecretKey generalKey(){
        String stringKey = secret.getKey();
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 创建jwt
     * @param userName
     * @param
     * @return
     * @throws Exception
     */
    public String createJWT(String userName, Long uid,String uuid){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey key = generalKey();
        MSub mSub = new MSub();
        mSub.setUid(uid);
        mSub.setUserName(userName);
        JwtBuilder builder = Jwts.builder()
                .setId(uuid)
                .setSubject(json.toJSONString(mSub))
                .signWith(signatureAlgorithm, key)
                .setExpiration(null);
        return builder.compact();
    }

    /**
     * 解密jwt
     * @param jwt
     * @return
     * @throws Exception
     */
    public String parseJWT(String jwt){
        SecretKey key = generalKey();
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
        return json.toJSONString(claims);

    }
}

