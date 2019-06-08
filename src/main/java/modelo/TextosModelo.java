package modelo;

public class TextosModelo {
	 private Integer id;
		private String titulo;
		private String descricao;
		private String fotoS;
		private String videoS;
		private String genero;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getTitulo() {
			return titulo;
		}

		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public String getFotoS() {
			return fotoS;
		}

		public void setFotoS(String fotoS) {
			this.fotoS = fotoS;
		}

		public String getVideoS() {
			return videoS;
		}

		public void setVideoS(String videoS) {
			this.videoS = videoS;
		}

		public String getGenero() {
			return genero;
		}

		public void setGenero(String genero) {
			this.genero = genero;
		}
}
