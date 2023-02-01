package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioAdicaoDeProdutosPage {
    private WebDriver navegador;

    public FormularioAdicaoDeProdutosPage(WebDriver navegador){
        this.navegador = navegador;
    }
    public FormularioAdicaoDeProdutosPage informarNomeDoProduto(String produtonome){
        navegador.findElement(By.id("produtonome")).sendKeys(produtonome);
        return this;
    }
    public FormularioAdicaoDeProdutosPage informarValorDoProduto(String produtoValor){
        navegador.findElement(By.id("produtovalor")).sendKeys(produtoValor);
        return this;
    }
    public FormularioAdicaoDeProdutosPage informarCoresDoProduto(String produtoCores){
        navegador.findElement(By.id("produtocores")).sendKeys(produtoCores);
        return this;
    }
    public ListaDeProdutosPage submeterFormularioDeProdutoComErro(){
        navegador.findElement(By.cssSelector("button[type='submit']")).click();
        return new ListaDeProdutosPage(navegador);
    }
    public FormularioDeEdicaoDeProdutoPage submeterFormularioDeProdutoComSucesso(){
        navegador.findElement(By.cssSelector("button[type='submit']")).click();
        return new FormularioDeEdicaoDeProdutoPage(navegador);
    }
}
