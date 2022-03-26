package org.mura.ssrjwt.exception;

/**
 * @author Akutagawa Murasame
 * @date 2022/3/22 22:43
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
