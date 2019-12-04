package com.exam.entity;

import com.exam.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 试卷表(Exam)实体类
 *
 * @author makejava
 * @since 2019-05-01 11:38:58
 */
@Data
public class Exam extends BaseEntity {
    private static final long serialVersionUID = 922283745093904434L;

    private Integer examId;
    //试卷名
    private String examName;
    //考试开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date examStartDate;
    //考试持续的时间，单位为分钟
    private Long examLastTime;
    //批阅者的id
    private Integer reviewerId;
    //试卷的总分
    private Double score;

    //学生考试总分
    private Double totalScore;

    /**
     * 试题的id
     */
    private Integer[] ids;
    /**
     * 试题的id变成str
     */
    private String idsStr;
    /**
     * 选择参加考试的学生的id
     */
    private Integer[] studentIds;

    /**
     * 当前考试是否参加
     */
    private Boolean accessed;

    /**
     * 当前试卷的学生的id
     */
    private Integer studentId;
    /**
     * 学生的姓名
     */
    private String studentName;
    /**
     * 是否已经批阅
     */
    private String reading;
}