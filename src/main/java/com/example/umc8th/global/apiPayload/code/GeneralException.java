package umc.umc.global.apiPayload.code;

public class GeneralException extends RuntimeException{

    //에러 상세 내용
    private final BaseErrorCode code;

    //생성자
    public GeneralException(BaseErrorCode code, String message) {
        this.code = code;
    }
}
