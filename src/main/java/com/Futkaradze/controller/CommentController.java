package com.Futkaradze.controller;

import com.Futkaradze.models.Comment;
import com.Futkaradze.models.User;
import com.Futkaradze.service.CommentService;
import com.Futkaradze.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    @Autowired
    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping("/api/comments/post/{postId}")
    public Comment createComment(@RequestBody Comment comment,
                                 @RequestHeader("Authorization") String jwt,
                                 @PathVariable("postId") Long postId) throws Exception {

        User user = userService.findUserByJwt(jwt);

        Comment createdComment = commentService.createComment(comment, postId, user.getId());

        return createdComment;
    }

    @PutMapping("/api/comments/like/{commentId}")
    public Comment likeComment(@RequestHeader("Authorization") String jwt,
                               @PathVariable Long commentId) throws Exception {

        User user = userService.findUserByJwt(jwt);

        Comment likedComment = commentService.likeComment(commentId, user.getId());

        return likedComment;
    }
}