package com.hexad.bakery.dto;

import java.util.List;


public class Results {

    private String code;
    private List<ExpectedPack> packs;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ExpectedPack> getPacks() {
        return packs;
    }

    public void setPacks(List<ExpectedPack> packs) {
        this.packs = packs;
    }

    public static class ExpectedPack {
        private Integer pack;
        private Integer count;

        public ExpectedPack() {}

        public ExpectedPack(Integer pack, Integer count) {
            this.pack = pack;
            this.count = count;
        }

        public Integer getPack() {
            return pack;
        }

        public void setPack(Integer pack) {
            this.pack = pack;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }
}
