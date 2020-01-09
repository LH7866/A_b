package com.lenovo.manufacture.zhy.Bean.three;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PeopleBean {
        /**
         * id : 1
         * peopleName : 李刚
         * icon : null
         * status : 0
         * talentMarketId : 1
         * gold : 200
         * hp : 100
         * content : 汽车工程师
         */

        private String id;
        private String peopleName;
        private Object icon;
        private String status;
        private String talentMarketId;
        private String gold;
        private String hp;
        private String content;

    @Override
    public String toString() {
        return "PeopleBean{" +
                "id='" + id + '\'' +
                ", peopleName='" + peopleName + '\'' +
                ", icon=" + icon +
                ", status='" + status + '\'' +
                ", talentMarketId='" + talentMarketId + '\'' +
                ", gold='" + gold + '\'' +
                ", hp='" + hp + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPeopleName() {
            return peopleName;
        }

        public void setPeopleName(String peopleName) {
            this.peopleName = peopleName;
        }

        public Object getIcon() {
            return icon;
        }

        public void setIcon(Object icon) {
            this.icon = icon;
        }

        public String getStatus() {
            switch (status){
                case "0":
                    status="工程师";
                    return status;
                case "1":
                    status="工人";
                    return status;
                case "2":
                    status="技术人员";
                    return status;
                case "3":
                    status="检测人员";
                    return status;
            }
            return status;
        }

        public void setStatus(String status) {

            this.status = status;
        }

        public String getTalentMarketId() {
            return talentMarketId;
        }

        public void setTalentMarketId(String talentMarketId) {
            this.talentMarketId = talentMarketId;
        }

        public String getGold() {
            return gold;
        }

        public void setGold(String gold) {
            this.gold = gold;
        }

        public String getHp() {
            return hp;
        }

        public void setHp(String hp) {
            this.hp = hp;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
