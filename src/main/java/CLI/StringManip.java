package CLI;

public class StringManip {
    public static Opcao getOpcao(String comando) throws IllegalArgumentException {
        if(comando.isEmpty()) {
            throw new IllegalArgumentException("inválido!");
        }
        if(comando.charAt(0) != '-') {
            throw new IllegalArgumentException("inválido!");
        }
        comando = comando.substring(1).toLowerCase().trim();

        String[] partes = comando.split("\\s+");

        Opcao opcao = new Opcao();

        switch (partes.length) {
            case 1:
                opcao.setTipo(enOpcao.UTIL);
                switch (partes[0]) {
                    case "logout":
                        opcao.setOperacao(enOpcao.LOGOUT);
                        break;
                    case "sair":
                        opcao.setOperacao(enOpcao.SAIR);
                        break;
                    default:
                        throw new IllegalArgumentException("inválido!");
                }
                break;

            case 2:
                switch (partes[0]) {
                    case "novo":
                        opcao.setOperacao(enOpcao.NOVO);
                        break;
                    case "mostrar":
                        opcao.setOperacao(enOpcao.MOSTRAR);
                        break;
                    case "remover":
                        opcao.setOperacao(enOpcao.REMOVER);
                        break;
                    case "alterar":
                        opcao.setOperacao(enOpcao.ALTERAR);
                        break;
                    default:
                        throw new IllegalArgumentException("inválido!");
                }

                switch (partes[1]) {
                    case "produto":
                        opcao.setTipo(enOpcao.PRODUTO);
                        break;
                    case "cliente":
                        opcao.setTipo(enOpcao.CLIENTE);
                        break;
                    case "pedido":
                        opcao.setTipo(enOpcao.PEDIDO);
                        break;
                    default:
                        throw new IllegalArgumentException("inválido!");
                }
                break;

            case 3:
                if (partes[0].equals("login")) {
                    opcao.setOperacao(enOpcao.LOGIN);
                    opcao.setTipo(enOpcao.UTIL);
                } else {
                    throw new IllegalArgumentException("inválido!");
                }
                break;
            default:
                throw new IllegalArgumentException("inválido!");
        }

        return opcao;
    }
}
