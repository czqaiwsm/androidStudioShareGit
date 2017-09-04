package com.share.learn.parse;

import com.google.gson.reflect.TypeToken;
import com.share.learn.bean.FeedBackDetail;
import com.share.learn.bean.FeedList;
import com.share.learn.bean.MsgDetail;
import com.share.learn.utils.URLConstants;
import com.volley.req.net.inferface.IParser;
import com.volley.req.parser.JsonParserBase;
import com.volley.req.parser.ParserUtil;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @desc 反馈列表解析
 * @creator caozhiqing
 * @data 2016/3/28
 */
public class FeeBackParse implements IParser {
    @Override
    public Object fromJson(JSONObject object) {
        return null;
    }

    @Override
    public Object fromJson(String json) {
        JsonParserBase result = ParserUtil.fromJsonBase(json, new TypeToken<JsonParserBase>() {
        }.getType());
        if(URLConstants.SUCCESS_CODE.equals(result.getRespCode())){
            return ParserUtil.fromJsonBase(json, new TypeToken<JsonParserBase<FeedList>>() {
            }.getType());
        }
        return result;
    }
}
