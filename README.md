# Spring Forum 

This project is a simple forum application built with Spring Boot. It allows users to create posts, comment on posts, and vote on posts.

## Features

The application includes the following functionalities:

- User registration and login
- Post creation, editing, and deletion
- Comment creation, editing, and deletion
- Voting on posts

## ERD Diagram

The following Entity-Relationship Diagram (ERD) shows the relationships between the entities in the application:

```mermaid
classDiagram
    class User {
        int user_id
        string username
        string email
        string password
        datetime created_at
    }
    
    class Post {
        int post_id
        int user_id
        string title
        string content
        datetime created_at
    }
    
    class Comment {
        int comment_id
        int post_id
        int user_id
        string content
        datetime created_at
    }
    
    class Vote {
        int vote_id
        int user_id
        int post_id
        string vote_type
        datetime created_at
    }

    User "1" *-- "N" Post : creates
    Post "1" *-- "N" Comment : contains
    User "1" *-- "N" Comment : writes
    User "1" *-- "N" Vote : casts
    Post "1" *-- "N" Vote : receives
```

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request with your changes.

## License

This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.
