package com.kekker.app.view;

import com.kekker.app.model.Comment;
import com.kekker.app.model.Kek;
import com.kekker.app.model.Reaction;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KekDto implements Serializable{

    private Long id;
    private String text;
    private String imageUrl;
    private String ownerNickName;
    private String ownerAvatarUrl;
    private Date createDate;
    private Set<ReactionDto> reactionsDto = new HashSet<ReactionDto>();
    private Set<CommentDto> commentsDto = new HashSet<CommentDto>();

    //sorry for this, Mr Andrii
//    public KekDto(Kek kek,  Set<Comment> comments, Set<Reaction> reactions)
//    {
//        text = kek.getText();
//        imageUrl = kek.getImageUrl();
//        createDate = kek.getCreateDate();
//        initReations(reactions);
//        initComments(comments);
//    }

    public KekDto()
    {

    }

    public KekDto(Kek k)
    {
        id=k.getId();
        text = k.getText();
        createDate = k.getCreateDate();
        ownerNickName = k.getOwner().getNickName();
        ownerAvatarUrl = k.getOwner().getAvatarUrl();
        initReations(k.getReactions());
        initComments(k.getComments());
    }


    private void initComments(Set<Comment> comments) {
        for(Comment com: comments)
        {
            commentsDto.add(new CommentDto(com.getText(), com.getCreationDate(), com.getOwner()));
        }
    }

    private void initReations(Set<Reaction> reactions) {
        for(Reaction react: reactions)
        {
            reactionsDto.add(new ReactionDto(react.getType(), react.getOwner()));
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Set<ReactionDto> getReactionsDto() {
        return reactionsDto;
    }

    public void setReactionsDto(Set<ReactionDto> reactionsDto) {
        this.reactionsDto = reactionsDto;
    }

    public Set<CommentDto> getCommentsDto() {
        return commentsDto;
    }

    public void setCommentsDto(Set<CommentDto> commentsDto) {
        this.commentsDto = commentsDto;
    }

    public String getOwnerNickName() {
        return ownerNickName;
    }

    public void setOwnerNickName(String ownerNickName) {
        this.ownerNickName = ownerNickName;
    }

    public String getOwnerAvatarUrl() {
        return ownerAvatarUrl;
    }

    public void setOwnerAvatarUrl(String ownerAvatarUrl) {
        this.ownerAvatarUrl = ownerAvatarUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
