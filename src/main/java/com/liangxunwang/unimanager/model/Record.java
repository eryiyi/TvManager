package com.liangxunwang.unimanager.model;

/**
 * Created by Administrator on 2016/2/14.
 */
public class Record {
    private String mm_msg_id;
    private String mm_emp_id;
    private String mm_msg_type;
    private String mm_msg_content;
    private String mm_msg_picurl;

    private String dateline;
    private String is_del;
    private String is_top;
    private String top_num;



    public String getMm_msg_id() {
        return mm_msg_id;
    }

    public void setMm_msg_id(String mm_msg_id) {
        this.mm_msg_id = mm_msg_id;
    }

    public String getMm_emp_id() {
        return mm_emp_id;
    }

    public void setMm_emp_id(String mm_emp_id) {
        this.mm_emp_id = mm_emp_id;
    }

    public String getMm_msg_type() {
        return mm_msg_type;
    }

    public void setMm_msg_type(String mm_msg_type) {
        this.mm_msg_type = mm_msg_type;
    }

    public String getMm_msg_content() {
        return mm_msg_content;
    }

    public void setMm_msg_content(String mm_msg_content) {
        this.mm_msg_content = mm_msg_content;
    }

    public String getMm_msg_picurl() {
        return mm_msg_picurl;
    }

    public void setMm_msg_picurl(String mm_msg_picurl) {
        this.mm_msg_picurl = mm_msg_picurl;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getIs_del() {
        return is_del;
    }

    public void setIs_del(String is_del) {
        this.is_del = is_del;
    }

    public String getIs_top() {
        return is_top;
    }

    public void setIs_top(String is_top) {
        this.is_top = is_top;
    }

    public String getTop_num() {
        return top_num;
    }

    public void setTop_num(String top_num) {
        this.top_num = top_num;
    }
}
