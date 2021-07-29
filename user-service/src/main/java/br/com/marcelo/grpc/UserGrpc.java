package br.com.marcelo.grpc;

import br.com.marcelo.common.BetType;
import br.com.marcelo.repository.UserRepository;
import br.com.marcelo.user.UserBetTypeUpdateRequest;
import br.com.marcelo.user.UserResponse;
import br.com.marcelo.user.UserSearchRequest;
import br.com.marcelo.user.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;


@GrpcService
@RequiredArgsConstructor
public class UserGrpc extends UserServiceGrpc.UserServiceImplBase {

    private final UserRepository repository;

    @Override
    public void getUser(UserSearchRequest request, StreamObserver<UserResponse> responseObserver) {
        UserResponse.Builder builder = UserResponse.newBuilder();
        repository.findById(request.getLoginId())
                .ifPresent(user ->
                        builder.setName(user.getName())
                                .setLongId(user.getLogin())
                                .setBetType(BetType.valueOf(user.getBetType())));
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void updateUserBetType(UserBetTypeUpdateRequest request, StreamObserver<UserResponse> responseObserver) {
        super.updateUserBetType(request, responseObserver);
    }
}
