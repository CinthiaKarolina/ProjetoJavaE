import com.sun.jna.Library;
import com.sun.jna.Native;
import java.util.Scanner;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.FileInputStream;

public class Main {


    public interface ImpressoraDLL extends Library {


        ImpressoraDLL INSTANCE = (ImpressoraDLL) Native.load(
                "C:\\Users\\cinthia_vieira\\Downloads\\Java-Aluno EM\\Java-Aluno EM\\Java-Aluno EM\\E1_Impressora01.dll",
                ImpressoraDLL.class
        );

        int abreConexaoImpressora(int tipo, String modelo, String conexao, int param);

        int fechaConexaoImpressora();

        int impressaoTexto(String dados, int posicao, int estilo, int tamanho);

        int Corte(int avanco);

        int impressaoQRCode(String dados, int tamanho, int nivelCorrecao);

        int impressaoCodigoBarras(int tipo, String dados, int altura, int largura, int HRI);

        int avancaPapel(int linhas);

        int statusImpressora(int param);

        int abreGavetaElgin();

        int abreGaveta(int pino, int ti, int tf);

        int sinalSonoro(int qtd, int tempoInicio, int tempoFim);

        int modoPagina();

        int LimpaBufferModoPagina();

        int ImprimeModoPagina();

        int ModoPadrao();

        int PosicaoImpressaoHorizontal(int posicao);

        int PosicaoImpressaoVertical(int posicao);

        int imprimeXMLSAT(String dados, int param);

        int imprimeXMLCancelamentoSAT(String dados, String assQRCode, int param);
    }

    private static boolean conexaoAberta = false;
    private static int tipo;
    private static int avanco;
    private static String modelo;
    private static String conexao;
    private static int parametro;
    private static String dados;
    private static int posicao;
    private static int estilo;
    private static int tamanho;
    private static int nivelCorrecao;
    private static String assQRCode;
    private static int 	param;
    private static final Scanner scanner = new Scanner(System.in);

    private static String capturarEntrada(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }


    //Faz o corte do papel quando tem impressao
    public static void Corte () {
        if (conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.Corte(avanco);
            if (retorno == 0) {
                System.out.println("Impressão cortada com sucesso.");
            }
            else {
                System.out.println("Erro ao cortar. Código de erro: " + retorno);
            }
        }
        else {
            System.out.println("Abra a conexão com a impressora.");
        }
    }


    //Configura a conexao quando o usuario digitar 1
    public static void configurarConexao() {

        System.out.println("Digite o tipo de conexão (ex: 1 para USB, 2 para serial, etc.): "); // digitar o tipo de conexao
        tipo = scanner.nextInt();

        System.out.println("Digite o modelo (ex: i7 para i7 , i8 para i8, i9 para i9, etc.)"); // digitar modelo da impressora
        modelo = scanner.nextLine();
        scanner.nextLine();

        System.out.println("Digite a conexao: (USB para USB, Bluetooth para Bluetooth, etc.)"); // digitar a conexao
        conexao = scanner.nextLine();

        parametro = 0;  // parametro desconsiderado por conta da configuracao dessa impressora

        System.out.println("\nConexao configurada com sucesso!\n");


    }


    //Abre a conexao quando a usuario digita 2
    public static void abrirConexao () {

        if (!conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.AbreConexaoImpressora(tipo, modelo, conexao, parametro);
            if (retorno == 0) { // se o retorno for 0, acontece abaixo
                conexaoAberta = true; // confirma conexao aberta
                System.out.println("Conexão aberta com sucesso.");
            } else {
                System.out.println("Erro ao abrir conexão. Código de erro: " + retorno); // digita que deu erro e mostra o retorno
            }
        } else {
            System.out.println("Conexão já está aberta."); // erro, conexao ja aberta
        }
    }


    //Fecha a conexao com impressora se o usuario digitar 0
    public static void fecharConexao () {
        int retorno = ImpressoraDLL.INSTANCE.FechaConexaoImpressora();
        if (retorno == 0) {
            conexaoAberta = false;
            System.out.println("Conexão fechada com sucesso.");
        } else {
            System.out.println("Erro ao fechar conexão conexão. Código de erro: " + retorno);
        }
    }



    //Imprime o texto na impressora DDL
   public static void impressaoTexto () {
        if (conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.ImpressaoTexto("Teste de impressao", 1, 4, 0);
            if (retorno == 0) {
                ImpressoraDLL.INSTANCE.AvancaPapel(5);
                System.out.println("Impressão realizada com sucesso!");
            } else {
                System.out.println("Erro ao abrir conexao. Codigo de erro: " + retorno);
            }
        } else {
            System.out.println("Abra a conexão com a impressora");
        }
    }


    // Imprime o QRCode na impressoraDLL
    public static void impressaoQRCode() {
        if (conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.ImpressaoQRCode("Teste de QRCode", 6, 4);
            if (retorno == 0) {
                ImpressoraDLL.INSTANCE.AvancaPapel(5);
                System.out.println("QR Code impresso com sucesso!");
            } else {
                System.out.println("Erro ao imprimir QR Code. Código: " + retorno);
            }
        } else {
            System.out.println("Abra a conexão com a impressora primeiro.");
        }
    }

    //Abre a gaveta elgin; nao precisa da conexao estar aberta
    public static void abreGavetaElgin() {
        if (conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.AbreGavetaElgin();
            if (retorno == 0) {
                System.out.println("Gaveta Elgin aberta com sucesso!");
            } else {
                System.out.println("Erro ao abrir gaveta Elgin. Código: " + retorno);
            }
        } else {
            System.out.println("Abra a conexão com a impressora primeiro.");
        }
    }

    public static void impressaoCodigoBarras() {  // Imprimir o código de barras
        if (conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.ImpressaoCodigoBarras(8, "{A012345678912", 100, 2, 3);
            if (retorno == 0) {
                ImpressoraDLL.INSTANCE.AvancaPapel(5);
                System.out.println("Código de barras impresso com sucesso!");
            } else {
                System.out.println("Erro ao imprimir código de barras. Código: " + retorno);
            }
        } else {
            System.out.println("Abra a conexão com a impressora primeiro.");
        }
    }


	public static void avancaPapel() {
        if (conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.AvancaPapel(2);
            if (retorno == 0) {
                System.out.println("Papel avançado com sucesso!");
            } else {
                System.out.println("Erro ao avançar papel. Código: " + retorno);
            }
        } else {
            System.out.println("Abra a conexão com a impressora primeiro.");
        }
    }


    public static void impressaoXMLSAT() {  // Imprimir em forma de SAT

        if (conexaoAberta) {
            String dados = "path=C:\\Users\\cinthia_vieira\\Downloads\\Java-Aluno EM\\Java-Aluno EM\\Java-Aluno EM\\XMLSAT.xml";    // Abre conexão com a impressora
            int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLSAT(dados,0);
            if (retorno == 0) {
                conexaoAberta = true;
                System.out.println("Impresso com sucesso.");
            } else {
                System.out.println("Erro ao imprimir. Código de erro: " + retorno);
            }
        } else {
            System.out.println("Precisa abrir conexao primeiro");
        }
    }


    //Cancela a impressao do XMLSAT
    public static void imprimeXMLCancelamentoSAT() {
        if (conexaoAberta) {
            String assQRCode = 							"Q5DLkpdRijIRGY6YSSNsTWK1TztHL1vD0V1Jc4spo/CEUqICEb9SFy82ym8EhBRZjbh3btsZhF+sjHqEMR159i4agru9x6KsepK/q0E2e5xlU5cv3m1woYfgHyOkWDNcSdMsS6bBh2Bpq6s89yJ9Q6qh/J8YHi306ce9Tqb/drKvN2XdE5noRSS32TAWuaQEVd7u+TrvXlOQsE3fHR1D5f1saUwQLPSdIv01NF6Ny7jZwjCwv1uNDgGZONJdlTJ6p0ccqnZvuE70aHOI09elpjEO6Cd+orI7XHHrFCwhFhAcbalc+ZfO5b/+vkyAHS6CYVFCDtYR9Hi5qgdk31v23w==";
            String dados = "path=C:\\Users\\cinthia_vieira\\Downloads\\Java-Aluno EM\\Java-Aluno EM\\Java-Aluno EM\\XMLSAT.xml";
            int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLCancelamentoSAT(dados, assQRCode, 0);
            if (retorno == 0) {
                System.out.println("Impressao de cancelamento do xml com sucesso.");
            } else {
                System.out.println("Erro ao imprimir o cancelamento do xml. Código: " + retorno);
            }
        } else {
            System.out.println("Nenhuma conexão está aberta.");
        }


    //Abre a gaveta
    public static void abrirGaveta () {
        if (conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.AbreGaveta(1,  5, 10); // pino, tempo i, tempo f
            if (retorno == 0) {
                System.out.println("Gaveta aberta");
            } else {
                System.out.println("Erro. retorno" + retorno);
            } }
        else {
            System.out.println("Abra a conexão.");
        }
    }

   //Emite um sinal sonoro quando o usuario digita 10
    public static void sinalSonoro () {
        if (conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.SinalSonoro(4, 5, 5); // qnt, tempo i, tempo f
            if (retorno == 0) {
                System.out.println("Sinal sonoro emitido com sucesso!");

            } else {
                System.out.println("Erro. retorno" + retorno);
            }
        }
        else{
            System.out.println("Abra a conexão com a sua impressora.");
        }

    }


    public static void main (String[]args){
        while (true) {
            System.out.println("\n*************************************************");
            System.out.println("**************** MENU IMPRESSORA *******************");
            System.out.println("*************************************************\n");

            System.out.println("1  - Configurar Conexao");
            System.out.println("2  - Abrir Conexao");
            System.out.println("3 - Impressao Texto");
            System.out.println("4 - Impressao QRCode");
            System.out.println("5 - Impressao Cod Barras");
            System.out.println("6 - Impressao XML SAT");
            System.out.println("7 - Impressao XML Canc SAT");
            System.out.println("8 - Abrir Gaveta Elgin");
            System.out.println("9 - Abrir Gaveta");
            System.out.println("10 - Sinal Sonoro");
            System.out.println("0 - Fechar Conexao e Sair");



            String escolha = capturarEntrada("\nDigite a opção desejada: ");

            if (escolha.equals("0")) {
                fecharConexao();
                System.out.println("Programa encerrado.");
                break;
            }

            switch (escolha) {
                case "1":
                    configurarConexao();
                    break;
                case "2":
                    abrirConexao();
                    break;
                case "3":
                    impressaoTexto();
                    ImpressoraDLL.INSTANCE.Corte(5);
                    break;

                case "4":
                    impressaoQRCode();
                    ImpressoraDLL.INSTANCE.Corte(5);
                    break;

                case "5":
                    impressaoCodigoBarras();
                    ImpressoraDLL.INSTANCE.Corte(5);

                    break;

                case "6":
                    impressaoXMLSAT();
                    ImpressoraDLL.INSTANCE.Corte(5);
                    break;

                case "7":
                    imprimeXMLCancelamentoSAT();
                    ImpressoraDLL.INSTANCE.Corte(5);
                    break;


                case "8":
                    abreGavetaElgin();
                    break;

                case "9":
                    abrirGaveta();
                    break;

                case "10":
                    sinalSonoro();
                    break;


                default:
                    System.out.println("OPÇÃO INVÁLIDA");
            }


        }


    }
}
