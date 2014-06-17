package br.com.senac.mol.models;

/**
 *
 * @author Equipe MOL
 */
public class RetornoUploadArquivo {

    private String nomeArquivo;
    private String diretorio;
    private boolean status;

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDiretorio() {
        return diretorio;
    }

    public void setDiretorio(String diretorio) {
        this.diretorio = diretorio;
    }

}
