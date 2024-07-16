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
        UUID id
        string username
        string email
        string password
        datetime created_at
        datetime updated_at
    }
    
    class Post {
        UUID id
        UUID user_id
        string title
        string content
        datetime created_at
        datetime updated_at
    }
    
    class Comment {
        UUID id
        UUID post_id
        UUID user_id
        string content
        datetime created_at
        datetime updated_at
    }
    
    class Vote {
        UUID id
        UUID user_id
        UUID post_id
        datetime created_at
        datetime updated_at
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
