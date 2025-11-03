# MorseTreeViewer 💗

**Projeto:** Visualizador de Árvore Morse em Java

# MorseCodeVisualizer

Visualizador de árvore de código Morse com decodificação de mensagens. Permite visualizar a árvore tanto no console quanto gerando um arquivo `.dot` para renderização no Graphviz.

## Sobre o projeto

Este projeto implementa a árvore de código Morse em Java.

- Cada nó da árvore representa uma letra ou número.
- Filhos à esquerda correspondem a `.` (ponto) e filhos à direita correspondem a `-` (traço).
- Visualização em cores:
  - 💜 Roxo → filhos à esquerda (`.`)
  - 💗 Rosa → filhos à direita (`-`)
- Arquivo `.dot` gerado segue a mesma lógica da árvore em memória, permitindo criar uma imagem da árvore completa usando Graphviz.

> Implementado no TDE2 como alternativa ao JavaFX da professora, usando apenas console e Graphviz para visualização.

## Pré-requisitos

- **JDK 20+** com **Preview Features** habilitadas.  

## Criar uma Run Configuration

Abra a classe MorseCodeTree.java.

Clique com o botão direito dentro do código → Run 'MorseCodeTree.main()'.

Se você quiser habilitar recursos de preview, faça assim:

Vá em Run → Edit Configurations → MorseCodeTree.

Em VM options, coloque:

--enable-preview

Salve e execute.

A IDE vai cuidar de compilar e rodar a aplicação com o JDK correto. Você verá o menu interativo no console da IDE.

## Como usar

Ao executar o programa, você verá um menu interativo:

1.🌳 **Visualizar árvore Morse no console**
2.📄 **Gerar arquivo `.dot` para abrir no Graphviz**
3. **Decodificar uma mensagem Morse** (letras separadas por espaço, palavras por `/`)
4. **Decodificar uma única letra Morse**
5. **Mostrar mapa padrão** (A-Z, 0-9)
0. **Sair do programa**

### Estrutura do projeto

- `src/MorseCodeTree.java` → Classe principal com menu e execução.
- `src/MorseTree.java` → Implementação da árvore de Morse, decodificação e geração do `.dot`.
- `src/MorseNode.java` → Nó da árvore Morse.
- `bin/` → Pasta onde o arquivo `.dot` e imagem da árvore será criada.

### Gerando imagem via Graphviz

### Usando Graphviz Online (Recomendado)

Basta abrir qualquer visualizador Graphviz online, colar o conteúdo do `.dot` e ele gerará a imagem automaticamente.

### Usando Graphviz Instalado

Se você gerar o arquivo `.dot`, pode criar uma imagem `.png` com o comando:
`bash
dot -Tpng bin/morse_tree.dot -o bin/morse_tree.png
