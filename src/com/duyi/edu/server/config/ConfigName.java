package com.duyi.edu.server.config;

public class ConfigName {

    @Must
    public static String PORT = "port";
    @Must
    public static String CORE_THREAD_SIZE = "core_thread_size";
    @Must
    public static String MAX_THREAD_SIZE = "max_thread_size";
    @Must
    public static String WAIT_QUEUE_SIZE = "wait_queue_size";

    public static String LOG_PATH = "log_path";
    public static String BASE_PATH = "base_path";
    public static String DEFAULT_PAGE = "default_page";

    public static String PAGE_404 = "page_404";
    public static String PAGE_500 = "page_500";


}
