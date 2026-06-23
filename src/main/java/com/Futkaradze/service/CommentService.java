package com.Futkaradze.service;

import com.Futkaradze.models.Comment;

public interface CommentService {

    public Comment createComment(Comment comment, Long postId, Long userId) throws Exception;

    public Comment findCommentById(Long commentId) throws Exception;

    public Comment likeComment(Long CommentId, Long userId) throws Exception;
}
