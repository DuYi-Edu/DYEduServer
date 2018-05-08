package com.duyi.edu.server.common.config;

public class ConfigName {

    //====================================================服务器基础配置=============================================================

    @Must
    public static String PORT = "port";
    @Must
    public static String CORE_THREAD_SIZE = "core_thread_size";
    @Must
    public static String MAX_THREAD_SIZE = "max_thread_size";
    @Must
    public static String WAIT_QUEUE_SIZE = "wait_queue_size";
    @Must
    public static String PATTERN = "pattern";
    public static String LOG_PATH = "log_path";

    //====================================================SimpleWebServer模式配置====================================================

    @Must(preConfig = "pattern", preValue = "SimpleWebServer")
    public static String BASE_PATH = "base_path";
    public static String DEFAULT_PAGE = "default_page";
    public static String PAGE_404 = "page_404";
    public static String PAGE_405 = "page_405";
    public static String PAGE_500 = "page_500";
    public static String PAGE_CACHE = "page_cache";

    //====================================================EduWebServer模式配置=======================================================

    @Must(preConfig = "pattern", preValue = "EduWebServer")
    public static String DATA_STORAGE_MEDIUM = "data_storage_medium";

    //--------------------MySql存储媒介配置----------------------
    @Must(preConfig = "data_storage_medium", preValue = "MySql")
    public static String DB_HOST = "db_host";

    @Must(preConfig = "data_storage_medium", preValue = "MySql")
    public static String DB_PORT = "db_port";

    @Must(preConfig = "data_storage_medium", preValue = "MySql")
    public static String DB_USER = "db_user";

    @Must(preConfig = "data_storage_medium", preValue = "MySql")
    public static String DB_PWD = "db_pwd";

    @Must(preConfig = "data_storage_medium", preValue = "MySql")
    public static String DB_NAME = "db_name";

    //--------------------DYF存储媒介配置-----------------------
    @Must(preConfig = "data_storage_medium", preValue = "DDF")
    public static String DATA_PATH = "data_path";
    @Must(preConfig = "data_storage_medium", preValue = "DDF")
    public static String USER_QUARANTINE = "user_quarantine";

    //====================================================WebContainer模式配置========================================================

    @Must(preConfig = "pattern", preValue = "WebContainer")
    public static String CONTAINER_PATH = "container_path";

    @Must(preConfig = "pattern", preValue = "WebContainer")
    public static String CONTAINER_CLASS_PATH = "container_class_path";


}
