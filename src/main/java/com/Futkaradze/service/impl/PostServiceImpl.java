package com.Futkaradze.service.impl;

import com.Futkaradze.models.Post;
import com.Futkaradze.models.User;
import com.Futkaradze.repository.PostRepository;
import com.Futkaradze.repository.UserRepository;
import com.Futkaradze.service.PostService;
import com.Futkaradze.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserService userService, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public Post createNewPost(Post post, Long userId) throws Exception {

        User user = userService.findUserById(userId);

        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setVideo(post.getVideo());
        newPost.setUser(user);

        return postRepository.save(newPost);
    } // good

    @Override
    public String deletePost(Long postId, Long userId) throws Exception {

        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if(post.getUser().getId()!=user.getId()){
            throw new Exception("STOP! You can't delete another users post");
        }

        postRepository.delete(post);
        return "Post has been deleted successfully";

    } // good

    @Override
    public List<Post> findPostByUserId(Long userId) {
        return postRepository.findPostByUserId(userId);
    } // good

    @Override
    public Post findPostById(Long postId) throws Exception {

        Optional<Post> opt = postRepository.findById(postId);

        if(opt.isEmpty()){
            throw new Exception("post not found with id " + postId);
        }
        return opt.get();
    } // good

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    } // good

    @Override
    public Post savedPost(Long postId, Long userId) throws Exception {

        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if(user.getSavedPost().contains(post)){
            user.getSavedPost().remove(post);
        }
        else user.getSavedPost().add(post);

        userRepository.save(user);

        return post;
    } // good

    @Override
    public Post likePost(Long postId, Long userId) throws Exception {

        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if(post.getLiked().contains(user)){ // Лайкнул ли юзер пост, если да, то его лайк удаляется (типо два раза на лайк не нажмешь)
            post.getLiked().remove(user);
        }
        else{
            post.getLiked().add(user); // если нет, то лайк добавляется на пост
        }

        return postRepository.saveAndFlush(post);
    }
}
