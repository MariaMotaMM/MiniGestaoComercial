package enums;

public enum UnidadeMedida {

	    UNIDADE("UN", "Unidade"),
	    PECA("PC", "Peça"),
	    PAR("PAR", "Par"),
	    KIT("KT", "Kit"),
	    
	    LITRO("L", "Litro"),
	    MILILITRO("ML", "Mililitro"),
	    
	    QUILO("KG", "Quilograma"),
	    GRAMA("G", "Grama"),
	    
	    METRO("M", "Metro"),
	    METRO_QUADRADO("M2", "Metro Quadrado"),
	    
	    CAIXA("CX", "Caixa"),
	    FARDO("FD", "Fardo");

	    private final String sigla;
	    private final String descricao;

	    
	    UnidadeMedida(String sigla, String descricao) {
	        this.sigla = sigla;
	        this.descricao = descricao;
	    }

	    
	    public String getSigla() {
	        return sigla;
	    }

	    public String getDescricao() {
	        return descricao;
	    }
	

}
