package patterns.entities;

public enum ActionStatusEnum {
    UNKNOWN(                    0, "Desconhecido"),
    RUNNING(                    1, "Em execução"),
    FAILED_AUTHENTICATION(      2, "Falha na autenticação"),
    SUCCESS(                    3, "Sucesso"),
    WARNING(                    4, "Atenção"),
    ERROR(                      5, "Erro"),
    EXPIRED_AUTHENTICATION(     6, "Autenticação expirada");

    private final long code;
    private final String description;

    ActionStatusEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static ActionStatusEnum valueOf(long code){
        for( ActionStatusEnum operationState: values() ){
            if(operationState.code == code){
                return  operationState;
            }
        }
        throw new RuntimeException("The code: "+code+" cannot be parsed to "+ ActionStatusEnum.class.getSimpleName());
    }

    public long getCode(){
        return this.code;
    }

    public String getDescription() {
        return description;
    }
}