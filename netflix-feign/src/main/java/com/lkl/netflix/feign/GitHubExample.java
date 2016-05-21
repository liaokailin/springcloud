package com.lkl.netflix.feign;

import feign.*;
import feign.codec.ErrorDecoder;
import feign.codec.Decoder;
import feign.gson.GsonDecoder;

import java.io.IOException;
import java.util.List;

/**
 * Created by liaokailin on 16/5/9.
 */
public class GitHubExample {

    interface GitHub {
        @RequestLine("GET /repos/{owner}/{repo}/contributors")
        List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);
    }


    public static void main(String... args) {
        Decoder decoder = new GsonDecoder();
        GitHub github = Feign.builder()
                .decoder(decoder)
                .errorDecoder(new GitHubErrorDecoder(decoder))
                .logger(new Logger.ErrorLogger())
                .logLevel(Logger.Level.BASIC)
                .target(GitHub.class, "https://api.github.com");

        System.out.println("Let's fetch and print a list of the contributors to this library.");
        List<Contributor> contributors = github.contributors("netflix", "feign");
        for (Contributor contributor : contributors) {
            System.out.println(contributor.login + " (" + contributor.contributions + ")");
        }

        System.out.println("Now, let's cause an error.");
        try {
            github.contributors("netflix", "some-unknown-project");
        } catch (GitHubClientError e) {
            System.out.println(e.getMessage());
        }
    }

    static class Contributor {
        String login;
        int contributions;
    }

    static class GitHubClientError extends RuntimeException {
        private String message; // parsed from json

        @Override
        public String getMessage() {
            return message;
        }
    }

    static class GitHubErrorDecoder implements ErrorDecoder {

        final Decoder decoder;
        final ErrorDecoder defaultDecoder = new ErrorDecoder.Default();

        GitHubErrorDecoder(Decoder decoder) {
            this.decoder = decoder;
        }

        @Override
        public Exception decode(String methodKey, Response response) {
            try {
                return (Exception) decoder.decode(response, GitHubClientError.class);
            } catch (IOException fallbackToDefault) {
                return defaultDecoder.decode(methodKey, response);
            }
        }
    }


}

