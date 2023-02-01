package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testes Web do Modulo de Produtos")
public class ProdutosTest {

    private WebDriver navegador;

    @BeforeEach
    public void beforEach() {
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\Chromedriver\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.get("http://165.227.93.41/lojinha-web/v2/");
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    @DisplayName("Nao e permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero() {

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioNovoProduto()
                .informarNomeDoProduto("XBOX 360 Slim")
                .informarValorDoProduto("000")
                .informarCoresDoProduto("Preto, Branco")
                .submeterFormularioDeProdutoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);

    }
    @Test
    @DisplayName("Nao e permitido registrar um produto com valor acima de Sete mil")
    public void testNaoEPermitidoRegistrarProdutoComValorMaiorSeteMil() {

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioNovoProduto()
                .informarNomeDoProduto("Iphone 12 Pro Max")
                .informarValorDoProduto("700001")
                .informarCoresDoProduto("Preto, Branco")
                .submeterFormularioDeProdutoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);

    }
    @Test
    @DisplayName("Posso adicionar produtos que estejam entre 0,01 e 7.000,00")
    public void testValoresPermitidos() {

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioNovoProduto()
                .informarNomeDoProduto("Cabo de Iphone")
                .informarValorDoProduto("150")
                .informarCoresDoProduto("Preto, Branco")
                .submeterFormularioDeProdutoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);

    }
    @Test
    @DisplayName("Posso adicionar produtos que esteja no limite de 7.000,00")
    public void testValoresPermitidosSeteMil() {

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioNovoProduto()
                .informarNomeDoProduto("Iphone 12 Pro Max")
                .informarValorDoProduto("700000")
                .informarCoresDoProduto("Preto, Branco")
                .submeterFormularioDeProdutoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);

    }

    @AfterEach
    public void afterEach(){
        navegador.quit();
    }

}
