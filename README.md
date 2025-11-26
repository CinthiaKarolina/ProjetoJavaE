Este projeto é um sistema abrangente para controle e gerenciamento de impressoras fiscais desenvolvido em Java, fazendo uso de JNA (Java Native Access) para integrar bibliotecas nativas. O sistema fornece funcionalidades completas para impressão fiscal, como SAT, códigos de barras, QR Codes e controle de periféricos.

Visão Geral
O Sistema de Controle de Impressora Fiscal é uma aplicação de Java que permite a integração completa com as impressoras fiscais da Elgin através da biblioteca E1_Impressora01.dll. O sistema oferece uma interface de linha de comando intuitiva e funcionalidades abrangentes para operações fiscais e comerciais.

Funcionalidades
Documentos Fiscais: Implementação completa para processamento e emissão de XML SAT e XML de cancelamento.
Identificação Eletrônica: Rotinas para impressão de QR Codes e diversos modelos de códigos de barras.
Periféricos Integrados: APIs para controle de gaveta e emissão de sinais sonoros.
Autenticação e Segurança: Sistema de gerenciamento de usuários baseado em login e criação de contas.
Interface Operacional: Menu interativo com arquitetura voltada à usabilidade e fluxo otimizado.
Conectividade: Compatibilidade com dispositivos via USB, Serial e outras interfaces suportadas.

Tecnologias Utilizadas
Java: Linguagem principal de desenvolvimento
JNA (Java Native Access): Interface para bibliotecas nativas
E1_Impressora01.dll: Biblioteca nativa para controle da impressora
Scanner: Interface de entrada para interação com usuário

 Funcionalidades Principais
Sistema de Autenticação
Criação de contas de usuário
Sistema de login seguro
Validação de credenciais em tempo real

 Gerenciamento de Conexão
Configuração flexível de parâmetros de conexão
Suporte para múltiplos tipos de interface (USB, Serial)
Monitoramento de status da conexão
Abertura e fechamento seguro de conexões

Impressão Avançada
Texto: Impressão formatada com controle de estilo e tamanho
QR Code: Geração e impressão de códigos QR personalizados
Código de Barras: Suporte para diversos padrões de código de barras
XML SAT: Processamento e impressão de documentos SAT
XML Cancelamento: Gestão de cancelamentos fiscais

Controle de Periféricos
Gaveta Elgin: Abertura automática com controle de timing
Gaveta Padrão: Compatibilidade com diversos modelos
Sinal Sonoro: Emissão de alertas audíveis configuráveis

Pré-requisitos
Sistema Operacional
Windows 10/11 (32-bit ou 64-bit)

Software
Java 8+
JNA (Java Native Access)
DLL E1_Impressora01.dll (driver de comunicação com a impressora)
Impressora térmica compatível com biblioteca Elgin

Arquivos Necessários
E1_Impressora01.dll - Biblioteca da impressora fiscal
XMLSAT.xml - Arquivo de exemplo para impressão SAT
CANC_SAT.xml - Arquivo de exemplo para cancelamento
Hardware
Impressora fiscal compatível com a biblioteca E1_Impressora01.dll
Cabo USB ou interface serial para conexão

INSTALAÇÃO


Instalação de bibliotecas nativas
import com.sun.jna.Library;
import com.sun.jna.Native;
import java.util.Scanner;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.FileInputStream;

Configuração da DLL e estrutura do codigo
 public interface ImpressoraDLL extends Library {
        ImpressoraDLL INSTANCE = (ImpressoraDLL) Native.load(
                "C:\\Users\\cinthia_vieira\\Downloads\\Java-Aluno EM\\Java-Aluno EM\\Java-Aluno EM\\E1_Impressora01.dll",
                ImpressoraDLL.class
        );
( Ajustar o caminho do código para o usuário correto se necessário [Cinthia_vieira] )

 int AbreConexaoImpressora(int tipo, String modelo, String conexao, int param);
 int FechaConexaoImpressora();
 int ImpressaoTexto(String dados, int posicao, int estilo, int tamanho); // métodos principais

// outros métodos



EXECUÇÃO DO PROGRAMA
Abra o arquivo Main.java no IntelliJ.
Clique no ícone de "play" Run na parte superior direita.
O console do IntelliJ abrirá com o menu:



**************** MENU IMPRESSORA *******************

1  - Configurar Conexao
2  - Abrir Conexao
3  - Impressao Texto
4  - Impressao QRCode
5  - Impressao Cod Barras
6  - Impressao XML SAT
7  - Impressao XML Canc SAT
8  - Abrir Gaveta Elgin
9  - Abrir Gaveta
10 - Sinal Sonoro
0  - Fechar Conexao e Sair

Exemplos de Uso

O funcionamento é interativo:

Digite 1 → Configurar Conexão

Digite 2 → Abrir Conexão

Digite 3 → Enviar texto para impressora

Digite 4 → Imprimir QR Code

Digite 5 → Imprimir código de barras

Digite 6 → Imprimir XML SAT

Digite 8/9 → Abrir gaveta

Digite 10 → Emitir sinal sonoro

Digite 0 → Fechar conexão e sair

Configurar Conexão:

Opção: 1
Tipo de conexão: 1 (USB)
Modelo: I9
Porta: USB
Parâmetro: 0

Imprimir QR Code:

Opção: 4
# Imprime QR Code com texto "Teste de impressao"
# Tamanho: 6, Corte 5


Fechando o projeto

Ao digitar 0, o programa:
Fecha a conexão com a impressora
Realiza corte (se configurado)

Encerra a aplicação.


- Erros Comuns
Erro: "Biblioteca não encontrada"

- Solução:
1. Verificar se a DLL está no caminho correto
2. Confirmar arquitetura (x64/x86)
3. Executar como administrador se necessário
Erro: "Conexão não pode ser aberta"

- Códigos de erro comuns:
Impressora não conectada
Porta em uso
Parâmetros inválidos

- Soluções:
1. Verificar cabo USB/Serial
2. Fechar outras aplicações que usam a impressora
3. Revisar parâmetros de configuração
Erro: "Arquivo XML não encontrado"

- Solução:
1. Verificar se os arquivos XML existem
2. Confirmar caminhos no código
3. Verificar permissões de leitura


- Desenvolvedores
Alexandre de Oliveira, 
Cinthia Vieira, 
Daniel Rhalley, 
Fabiano Tavares, 
Giovana Migliorini.



