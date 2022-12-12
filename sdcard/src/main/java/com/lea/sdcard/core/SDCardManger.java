package com.lea.sdcard.core;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import com.lea.sdcard.utils.SDCardTimeUitls;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by druid on 2018/11/1.
 */

public class SDCardManger {
    public static final String TAG = SDCardManger.class.getName();
    public Application application;

    static SDCardManger instance = null;
    private String company_name = "";
    private String product_name = "";
    private String root_path = "";
    String path_0_company = "";//一级公司根目录
    String path_1_product = "";//二级产品根目录
    String path_2_media = "madia";//媒体目录
    public String path_2_cache = "cache";//缓存目录
    public String path_2_data = "data";//数据目录
    public String path_2_download = "down";//下载目录
    public String path_2_log = "log";//日志目录
    public String path_2_bin = "bin";//固件下载目录
    public String path_2_tools = "tools";//功能目录
    public String path_2_export = "export";//文件导出目录
    public String path_media_3_voice = "voice";//声音媒体目录
    public String path_media_3_img = "img";//图片媒体目录
    public String path_media_3_vedio = "vedio";//视频媒体目录
    public String path_media_3_data = "data";//拍照图片保存目录
    public String path_3_bin_debug = "debug";//固件debug目录
    public String path_3_bin_release = "release";//固件release目录
    public String path_3_bin_select = "select";//固件select目录
    public String path_3_bin_ota_v2 = "ota_v2";//固件v2目录
    public String path_3_tools_mark = "mark";//打标功能目录
    public String path_3_tools_transfer = "transfer";//数据助手功能目录
    public String path_3_tools_search = "search";//蓝牙搜索记录
    public String path_4_bin_select_debug = "debug";//固件select目录
    public String path_4_bin_select_release = "release";//固件select目录
    public String path_4_mark_data = "data";//打标数据目录
    public String path_4_transfer_data = "data";//数据助手数据目录
    public String path_4_search_data = "data";//蓝牙搜索数据目录
    static final String log_name = "log.txt";//日志名称

    /**
     * SDCard管理对象初始化
     */
    public static void init(Application application, String company_name, String product_name) {
        String root_path = "";
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            root_path = application.getExternalFilesDir("").getPath();
        } else {
            root_path = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        instance = new SDCardManger(company_name, product_name, root_path);
        instance.application = application;
    }

    private SDCardManger(String company_name, String product_name, String root_path) {
        this.company_name = company_name;
        this.product_name = product_name;
        this.root_path = root_path;
        createFile();
    }

    public static SDCardManger getInstance() {
        if (instance == null) {
            Log.e(TAG, "SDCardManger need init()");
            return null;
        }
        return instance;
    }

    private void createFile() {
        ArrayList<String> paths = new ArrayList<>();
        path_0_company = root_path + File.separator + company_name;//../Druid
        paths.add(path_0_company);
        path_1_product = path_0_company + File.separator + product_name;//../Druid/Bird
        paths.add(path_1_product);
        path_2_media = path_1_product + File.separator + path_2_media;//../Druid/Bird/media
        paths.add(path_2_media);
        path_2_cache = path_1_product + File.separator + path_2_cache;//../Druid/Bird/cache
        paths.add(path_2_cache);
        path_2_data = path_1_product + File.separator + path_2_data;//../Druid/Bird/data
        paths.add(path_2_data);
        path_2_download = path_1_product + File.separator + path_2_download;//../Druid/Bird/down
        paths.add(path_2_download);
        path_2_log = path_1_product + File.separator + path_2_log;//../Druid/Bird/log
        paths.add(path_2_log);
        path_2_export = path_1_product + File.separator + path_2_export;//../Druid/Bird/export
        paths.add(path_2_export);
        //固件目录，功能模块目录
        path_2_bin = path_1_product + File.separator + path_2_bin;//../Druid/Bird/bin
        paths.add(path_2_bin);
        path_2_tools = path_1_product + File.separator + path_2_tools;//../Druid/Bird/tools
        paths.add(path_2_tools);
        //
        path_media_3_voice = path_2_media + File.separator + path_media_3_voice;//../Druid/Bird/media/voice
        paths.add(path_media_3_voice);
        path_media_3_img = path_2_media + File.separator + path_media_3_img;//../Druid/Bird/media/img
        paths.add(path_media_3_img);
        path_media_3_vedio = path_2_media + File.separator + path_media_3_vedio;//../Druid/Bird/media/vedio
        paths.add(path_media_3_vedio);
        path_media_3_data = path_2_media + File.separator + path_media_3_data;//../Druid/Bird/media/data
        paths.add(path_media_3_data);
        //固件目录，功能模块目录
        path_3_bin_debug = path_2_bin + File.separator + path_3_bin_debug;//../Druid/Bird/bin/debug
        paths.add(path_3_bin_debug);
        path_3_bin_release = path_2_bin + File.separator + path_3_bin_release;//../Druid/Bird/bin/release
        paths.add(path_3_bin_release);
        path_3_bin_select = path_2_bin + File.separator + path_3_bin_select;//../Druid/Bird/bin/select
        paths.add(path_3_bin_select);
        path_3_bin_ota_v2 = path_2_bin + File.separator + path_3_bin_ota_v2;//../Druid/Bird/bin/ota_v2
        paths.add(path_3_bin_ota_v2);
        path_3_tools_mark = path_2_tools + File.separator + path_3_tools_mark;//../Druid/Bird/tools/mark
        paths.add(path_3_tools_mark);
        path_3_tools_transfer = path_2_tools + File.separator + path_3_tools_transfer;//../Druid/Bird/tools/transfer
        paths.add(path_3_tools_transfer);
        path_3_tools_search = path_2_tools + File.separator + path_3_tools_search;//../Druid/Bird/tools/search
        paths.add(path_3_tools_search);
        //固件目录，功能模块目录
        path_4_bin_select_debug = path_3_bin_select + File.separator + path_4_bin_select_debug;//../Druid/Bird/bin/select/debug
        paths.add(path_4_bin_select_debug);
        path_4_bin_select_release = path_3_bin_select + File.separator + path_4_bin_select_release;//../Druid/Bird/bin/select/release
        paths.add(path_4_bin_select_release);
        //
        path_4_mark_data = path_3_tools_mark + File.separator + path_4_mark_data;//../Druid/Bird/tools/mark/data
        paths.add(path_4_mark_data);
        path_4_transfer_data = path_3_tools_transfer + File.separator + path_4_transfer_data;//../Druid/Bird/tools/transfer/data
        paths.add(path_4_transfer_data);
        path_4_search_data = path_3_tools_search + File.separator + path_4_search_data;//../Druid/Bird/tools/search/data
        paths.add(path_4_search_data);
        //
        for (String path : paths) {
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }
        paths.clear();
        createLogFile(log_name);
    }

    void createLogFile(String filename) {
        try {
            String log_path = path_2_log + File.separator + filename;
            File file = new File(path_2_log);
            if (!file.exists()) {
                file.mkdirs();
            }
            File dir = new File(log_path);
            if (!dir.exists()) {
                dir.createNewFile();
            }
        } catch (Exception ex) {
        }
    }

    public void printfLog(String str) {
        createLogFile("log.txt");
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            String log_path = path_2_log + File.separator + log_name;
            fw = new FileWriter(log_path, true);
            bw = new BufferedWriter(fw);
            String myreadline = "[" + SDCardTimeUitls.getCurrentDate() + "]" + str;
            bw.write(myreadline + "\n");
            bw.newLine();
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                bw.close();
                fw.close();
            } catch (IOException e1) {
            }
        }
    }
}
