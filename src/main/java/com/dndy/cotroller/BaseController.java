package com.dndy.cotroller;

import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.Serializable;

/**
 * 基础控制类
 */
public class BaseController implements Serializable {

	protected static Logger logger = LogManager.getLogger();

	private static final long serialVersionUID = 6357869213649815390L;

	protected JSONObject json = new JSONObject();
}
