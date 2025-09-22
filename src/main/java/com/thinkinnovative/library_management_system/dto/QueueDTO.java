package com.thinkinnovative.library_management_system.dto;

public class QueueDTO {
    private Integer queue_id;
    private Integer member_id;
    private String  member_name;

    public QueueDTO(Integer queue_id, Integer member_id, String member_name, Integer position) {
        this.queue_id = queue_id;
        this.member_id = member_id;
        this.member_name = member_name;
        this.position = position;
    }

    private Integer position;
    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }


    public Integer getMember_id() {
        return member_id;
    }

    public void setMember_id(Integer member_id) {
        this.member_id = member_id;
    }

    public Integer getQueue_id() {
        return queue_id;
    }

    public void setQueue_id(Integer queue_id) {
        this.queue_id = queue_id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public QueueDTO(Integer queue_id, Integer member_id, Integer position) {
        this.queue_id = queue_id;
        this.member_id = member_id;
        this.position = position;
    }
}
