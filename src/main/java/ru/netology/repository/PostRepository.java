package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {

    private final Map<Long, Post> posts;
    private final AtomicLong postID;

    public PostRepository() {
        posts = new ConcurrentHashMap<>();
        postID = new AtomicLong(0);
    }

    public List<Post> all() {
        return new ArrayList<>(posts.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(posts.get(id));
    }

    public Post save(Post post) {
        long postExistingID = post.getId();
        if (postExistingID > 0 && posts.containsKey(postExistingID)) {
            posts.replace(postExistingID, post);
        } else {
            long newPostID = postExistingID == 0 ? postID.incrementAndGet() : postExistingID;
            post.setId(newPostID);
            posts.put(newPostID, post);
        }
        return post;
    }

    public void removeById(long id) {
        posts.remove(id);
    }
}
