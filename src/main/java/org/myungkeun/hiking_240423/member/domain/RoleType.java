package org.myungkeun.hiking_240423.member.domain;

public enum RoleType {
    ADMIN("관리자"),
    OPERATOR("운영자"),
    NORMAL("일반"),
    VIP("우수");

    private String displayName;

    RoleType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}