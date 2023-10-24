package net.emapp.webfluxsecurity.service;

//@Service
//public class FavoriteBookService {
//
//    private UserRepository userRepository;
//    private BookRepository bookRepository;
//    private BookUserEntity bookUserEntity;
//
//    public FavoriteBookService(UserRepository userRepository,
//                               BookRepository bookRepository,
//                               BookUserEntity bookUserEntity) {
//        this.userRepository = userRepository;
//        this.bookRepository = bookRepository;
//        this.bookUserEntity = bookUserEntity;
//    }
//    public Mono<UserEntity> addBookToFavorite(Long userId, Long bookId) {
//        return userRepository.findById(userId)
//                .flatMap(user -> bookRepository.findById(bookId)
//                        .map(book -> {
//                            user.getFavoriteBooks().add(book);
//                            return user;
//                        })
//                        .flatMap(userRepository::save));
//    }
//
//}
