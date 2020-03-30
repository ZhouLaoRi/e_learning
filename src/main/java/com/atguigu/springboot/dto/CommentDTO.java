package com.atguigu.springboot.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by limi on 2017/10/14.
 */
@Data
public class CommentDTO {

    private Integer commentId;

    private String nickname;

    //20200330晚上修改 把原有的nickname属性当作replyname，nick通过userId去获得（没办法懒得数据库加一个字段了）
    private String replyname;

    private String avatar;

    private Date commentDate;

    private String commentText;

    //点赞的数量
    private Integer commentLike;

    //是不是博主
    private boolean adminComment;

    //回复节点
    private List<CommentDTO> replyComments = new ArrayList<>();

    //父节点，用来保存@某人  这个符号 ,不需要parentId  直接就是父节点
    // 20200330晚上修改，去掉了这个parent了，不需要这个父节点的信息。单纯当作位置去判断就好了
    //private CommentDTO parentComment;



}
