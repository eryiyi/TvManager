package com.liangxunwang.unimanager.model;

/**
 * Created by zhl on 2016/12/19.
 */
public class HelpType {
    private String help_type_id;
    private String help_type_name;
    private String help_type_f_id;
    private String top_number;
    private String is_type;

    public String getIs_type() {
        return is_type;
    }

    public void setIs_type(String is_type) {
        this.is_type = is_type;
    }

    public String getTop_number() {
        return top_number;
    }

    public void setTop_number(String top_number) {
        this.top_number = top_number;
    }

    public String getHelp_type_id() {
        return help_type_id;
    }

    public void setHelp_type_id(String help_type_id) {
        this.help_type_id = help_type_id;
    }

    public String getHelp_type_name() {
        return help_type_name;
    }

    public void setHelp_type_name(String help_type_name) {
        this.help_type_name = help_type_name;
    }

    public String getHelp_type_f_id() {
        return help_type_f_id;
    }

    public void setHelp_type_f_id(String help_type_f_id) {
        this.help_type_f_id = help_type_f_id;
    }
}
