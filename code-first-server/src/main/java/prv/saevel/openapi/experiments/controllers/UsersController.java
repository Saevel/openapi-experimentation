package prv.saevel.openapi.experiments.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import prv.saevel.openapi.experiments.models.User;
import reactor.core.publisher.Mono;

import java.util.OptionalInt;

@RestController("users")
public class UsersController {
    @Operation(
            method = "GET",
            summary = "Get User by id",
            responses = {
                    @ApiResponse(
                            description = "User successfully found",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class)
                            )
                    ),
                    @ApiResponse(
                            description = "User not found",
                            responseCode =  "404",
                            content = @Content(
                                    mediaType = "text/plain",
                                    schema = @Schema(implementation = String.class, example = "User not found")
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public Mono<ServerResponse> getUserById(@PathVariable("id") int id){
        return Mono.just(new User(id, "Kamil", "Owczarek", OptionalInt.of(30)))
                .flatMap(user -> ServerResponse.ok().bodyValue(user))
                .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND).bodyValue("User not found"));
    }
}
