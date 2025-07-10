package org.league.ranking;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import io.quarkus.redis.client.RedisClient;
import io.vertx.redis.client.Response as RedisResponse;
import jakarta.inject.Inject;
import java.util.List;

@Path("/rankings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RankingResource {

    @Inject
    RedisClient redis;

    @POST
    public Response create(Ranking ranking) {
        String key = "ranking:" + ranking.team;
        redis.hmset(List.of(key, "points", String.valueOf(ranking.points)));
        System.out.println("EVENT: ranking.updated -> " + key);
        return Response.status(Response.Status.CREATED).entity(ranking).build();
    }

    @GET
    public List<String> list() {
        return redis.keys("ranking:*");
    }
}
