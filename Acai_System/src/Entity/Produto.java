package Entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Produto {
	private String descricao, categoria;
	private BigDecimal valor;
	private int idProduto, ativo;
	private float quantidade;
	private Date dataInclusao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	@Override
	public String toString() {
		return "Produto [descricao=" + descricao + ", categoria=" + categoria + ", valor=" + valor + ", idProduto="
				+ idProduto + ", ativo=" + ativo + ", quantidade=" + quantidade + ", dataInclusao=" + dataInclusao
				+ "]";
	}

	
}
