package willem.base.tool.http;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Stopwatch;
import okhttp3.*;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author weiyu
 * @Description
 * @Date 2019/3/22 18:12
 */
public class HttpUtil {
    private OkHttpClient client;
    private ThreadLocal<Stopwatch> threadWatch;

    public enum DataType{
        JSON("application/json"),
        FORM("application/x-www-form-urlencoded");

        private String type;

        DataType(String type){
            this.type = type;
        }

        public String getType(){
            return this.type;
        }
    }

    public HttpUtil(){
        init();
    }

    public void init(){
        client = new OkHttpClient().newBuilder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(10000, TimeUnit.MILLISECONDS)
                .build();
        threadWatch = ThreadLocal.withInitial(Stopwatch::createUnstarted);
    }

    private Stopwatch getStopWatch(){
        Stopwatch sw = threadWatch.get();
        if (sw == null){
            sw = Stopwatch.createUnstarted();
            threadWatch.set(sw);
        }
        return sw;
    }

    /**
     * get请求
     * @param url
     * @return
     */
    public String get(String url, Map<String,String> headerParam) {
        Stopwatch sw = getStopWatch();
        StringBuilder info = new StringBuilder("http[get] => "+url);
        if (headerParam == null) {
            headerParam = Collections.emptyMap();
        }
        Headers headers = Headers.of(headerParam);
        Request.Builder requestBuilder = new Request.Builder();
        if (headers.size() > 0) {
            requestBuilder = requestBuilder.headers(headers);
        }
        Response response = null;
        String result = null;
        try {
            sw.start();
            response = client.newCall(requestBuilder.url(url).get().build()).execute();
            //只做http的检查，具体业务报错需自行判断
            if (!response.isSuccessful()) {
                throw new Exception(String.format("code[%s],error[%s]", response.code(), response.message()));
            }
            result = response.body().string();
            System.out.println(info.append(" cost ").append(sw.elapsed(TimeUnit.MILLISECONDS)).append(" ms").toString());
        } catch (Exception e) {
            System.err.println(info.append(" occur error").toString());
        } finally {
            sw.stop().reset();
            if (null != response) {
                response.close();
            }
            return result;
        }
    }

    /**
     *
     * @param url
     * @param headerParam
     * @param param
     * @return
     */
    public String postForm(String url, Map<String,String> headerParam, Map<String, Object>param) {
        return post(url, headerParam, param, DataType.FORM);
    }

    /**
     *
     * @param url
     * @param headerParam
     * @param param
     * @return
     */
    public String postJson(String url, Map<String,String> headerParam, Map<String, Object>param){
        return post(url, headerParam, param, DataType.JSON);
    }

    /**
     * post请求
     * @param url
     * @param param
     * @param dataType
     * @return
     */
    public String post(String url, Map<String,String> headerParam, Map<String, Object>param, DataType dataType) {
        Stopwatch sw = getStopWatch();
        StringBuilder info = new StringBuilder("http[post] => "+url);
        RequestBody body;
        switch (dataType) {
            case FORM:
                FormBody.Builder builder = new FormBody.Builder();
                for (Map.Entry<String, Object> item : param.entrySet()) {
                    builder = builder.add(item.getKey(),item.getValue().toString());
                }
                body = builder.build();
                break;
            case JSON:
            default:
                String paramStr = JSONObject.toJSONString(param);
                body = RequestBody.create(paramStr.getBytes(),MediaType.parse(dataType.getType()));
                break;
        }

        if (headerParam == null) {
            headerParam = Collections.emptyMap();
        }
        Headers headers = Headers.of(headerParam);
        Request.Builder requestBuilder = new Request.Builder();
        if (headers.size() > 0) {
            requestBuilder = requestBuilder.headers(headers);
        }
        Response response = null;
        String result = null;
        try {
            sw.start();
            response = client.newCall(requestBuilder.url(url).post(body).build()).execute();
            //只做http的检查，具体业务报错需自行判断
            if (!response.isSuccessful()) {
                throw new Exception(String.format("code[%s],error[%s]", response.code(), response.message()));
            }
            result = response.body().string();
            System.out.println(info.append(" cost ").append(sw.elapsed(TimeUnit.MILLISECONDS)).append(" ms").toString());
        } catch (Exception e) {
            System.err.println(info.append(" occur error").toString());
        } finally {
            sw.stop().reset();
            if (null != response) {
                response.close();
            }
            return result;
        }
    }
}
