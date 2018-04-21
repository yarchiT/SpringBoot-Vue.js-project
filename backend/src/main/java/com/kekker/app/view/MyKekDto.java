package com.kekker.app.view;

import com.kekker.app.model.Comment;
import com.kekker.app.model.Kek;
import com.kekker.app.model.Reaction;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyKekDto implements Serializable{

    private String text;
    private byte[] image;
    private Date createDate;
    private Set<ReactionDto> reactionsDto = new HashSet<ReactionDto>();
    private Set<CommentDto> commentsDto = new HashSet<CommentDto>();

    //sorry for this, Mr Andrii
    public MyKekDto(Kek kek,  Set<Comment> comments, Set<Reaction> reactions)
    {
        text = kek.getText();
        image = kek.getImage();
        createDate = kek.getCreateDate();
        initReations(reactions);
        initComments(comments);
    }

    public MyKekDto()
    {

    }

    public MyKekDto(Kek k)
    {
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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
}
