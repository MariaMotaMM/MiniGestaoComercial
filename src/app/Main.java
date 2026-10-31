package app;

import java.math.BigDecimal;
import javax.swing.JOptionPane;

import enums.TipoEntrada;
import enums.TipoSaida;
import enums.UnidadeMedida;
import model.Carrinho;
import model.CartaoCredito;
import model.CartaoDebito;
import model.Cliente;
import model.Estoque;
import model.PagamentoDinheiro;
import model.PagamentoPix;
import model.Produto;
import service.VendasService;

public class Main {
    public static void main(String[] args) {
        Produto produto = null;
        Estoque estoque = null;
        Cliente cliente = null;
        Carrinho carrinho = new Carrinho();

        boolean executando = true;
        while (executando) {
            String menu = "=== MINI GESTÃO COMERCIAL ===\n\n" +
                          "1. Cadastrar Produto e Estoque\n" +
                          "2. Movimentar Estoque (Entrada / Saída)\n" +
                          "3. Cadastrar Cliente (Testar Validação de CPF)\n" +
                          "4. Simular Venda Completa\n" +
                          "5. Ver Relatórios de Estoque\n" +
                          "6. Sair\n\n" +
                          "Escolha uma opção:";

            String opcaoStr = JOptionPane.showInputDialog(null, menu, "Mini Gestão Comercial", JOptionPane.QUESTION_MESSAGE);
            
            if (opcaoStr == null) {

            }

            try {
                int opcao = Integer.parseInt(opcaoStr);
                switch (opcao) {
                    case 1:
                        
                        String nome = JOptionPane.showInputDialog("Nome do Produto:");
                        if (nome == null) break;
                        String desc = JOptionPane.showInputDialog("Descrição do Produto:");
                        String marca = JOptionPane.showInputDialog("Marca:");
                        String precoStr = JOptionPane.showInputDialog("Preço (ex: 150.00):");
                        String qtdMinStr = JOptionPane.showInputDialog("Quantidade Mínima em Estoque:");
                        String qtdAtualStr = JOptionPane.showInputDialog("Quantidade Inicial em Estoque:");

                        produto = new Produto()
                                .setNomeProduto(nome)
                                .setDescricao(desc)
                                .setMarca(marca)
                                .setPreco(new BigDecimal(precoStr))
                                .setUnidadeMedida(UnidadeMedida.UNIDADE);

                        estoque = new Estoque()
                                .setProduto(produto)
                                .setQuantidadeMinima(Integer.parseInt(qtdMinStr))
                                .setQuantidadeAtual(Integer.parseInt(qtdAtualStr));

                        JOptionPane.showMessageDialog(null, "Produto e Estoque cadastrados com sucesso!\n\n" + produto.toString());
                        break;

                    case 2:
                       
                        if (produto == null || estoque == null) {
                            JOptionPane.showMessageDialog(null, "Cadastre um produto e estoque primeiro (Opção 1).", "Aviso", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        String tipoMov = JOptionPane.showInputDialog("Escolha o tipo de movimentação:\n1. Entrada\n2. Saída");
                        if (tipoMov != null && tipoMov.equals("1")) {
                            String qtdEntrada = JOptionPane.showInputDialog("Quantidade para Entrada:");
                            String descEntrada = JOptionPane.showInputDialog("Motivo/Descrição da Entrada (ex: Abastecimento):");
                            estoque.aumentarEstoque(TipoEntrada.ABASTECIMENTO, descEntrada, Integer.parseInt(qtdEntrada));
                            JOptionPane.showMessageDialog(null, "Entrada realizada com sucesso!\nEstoque atual: " + estoque.getQuantidadeAtual());
                        } else if (tipoMov != null && tipoMov.equals("2")) {
                            String qtdSaida = JOptionPane.showInputDialog("Quantidade para Saída:");
                            String descSaida = JOptionPane.showInputDialog("Motivo da Saída (ex: Avarias / Outros):");
                            estoque.reduzirEstoque(TipoSaida.OUTROS, Integer.parseInt(qtdSaida), descSaida);
                            JOptionPane.showMessageDialog(null, "Saída realizada com sucesso!\nEstoque atual: " + estoque.getQuantidadeAtual());
                        }
                        break;

                    case 3:
                       
                        String nomeCli = JOptionPane.showInputDialog("Nome Completo do Cliente:");
                        if (nomeCli == null) break;
                        String cpfCli = JOptionPane.showInputDialog("CPF do Cliente (apenas os 11 números):");
                        String telCli = JOptionPane.showInputDialog("Telefone de Contato:");
                        String emailCli = JOptionPane.showInputDialog("E-mail:");

                        cliente = new Cliente()
                                .setNomeCompleto(nomeCli)
                                .setCPF(cpfCli)
                                .setTelefoneContato(telCli)
                                .setEmail(emailCli);

                        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!\n\n" + cliente.toString());
                        break;

                    case 4:
                        
                        if (produto == null || estoque == null) {
                            JOptionPane.showMessageDialog(null, "Cadastre um produto primeiro (Opção 1).", "Aviso", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        if (cliente == null) {
                            JOptionPane.showMessageDialog(null, "Cadastre um cliente primeiro (Opção 3).", "Aviso", JOptionPane.WARNING_MESSAGE);
                            break;
                        }

                        String qtdVendaStr = JOptionPane.showInputDialog("Quantidade do produto '" + produto.getNomeProduto() + "' para o carrinho:");
                        int qtdVenda = Integer.parseInt(qtdVendaStr);

                        carrinho.adicionarItem(produto, qtdVenda);

                        String formaPag = JOptionPane.showInputDialog("Escolha a Forma de Pagamento:\n1. Cartão de Crédito\n2. Cartão de Débito\n3. Pix\n4. Dinheiro");
                        VendasService vendaService = new VendasService();
                        vendaService.setCliente(cliente);
                        vendaService.adicionarCarrinho(carrinho);

                        if (formaPag != null) {
                            if (formaPag.equals("1")) {
                                String parcelasStr = JOptionPane.showInputDialog("Quantidade de Parcelas (1 a 12):");
                                CartaoCredito cartao = new CartaoCredito();
                                vendaService.iniciarVenda(cartao, Integer.parseInt(parcelasStr));
                            } else if (formaPag.equals("2")) {
                                vendaService.iniciarVenda(new CartaoDebito(), 1);
                            } else if (formaPag.equals("3")) {
                                vendaService.iniciarVenda(new PagamentoPix(), 1);
                            } else {
                                vendaService.iniciarVenda(new PagamentoDinheiro(), 1);
                            }
                        }

                        vendaService.aprovarPagamento();
                        vendaService.mudarEstadoEnvio(); 
                        vendaService.mudarEstadoEnvio(); 
                        vendaService.finalizarPedido();  

                        JOptionPane.showMessageDialog(null, 
                            "Venda finalizada com sucesso!\n" +
                            "Cliente: " + cliente.getNomeCompleto() + "\n" +
                            "Valor Total: R$ " + carrinho.calcularValorTotal() + "\n" +
                            "Estoque Atual do Produto: " + estoque.getQuantidadeAtual(),
                            "Sucesso na Venda", JOptionPane.INFORMATION_MESSAGE);
                        break;

                    case 5:
                        
                        if (estoque == null) {
                            JOptionPane.showMessageDialog(null, "Crie um produto e estoque primeiro (Opção 1).", "Aviso", JOptionPane.WARNING_MESSAGE);
                            break;
                        }

                        String relatorioOp = JOptionPane.showInputDialog(
                            "Escolha o Relatório:\n" +
                            "1. Relatório Geral de Movimentações\n" +
                            "2. Relatório de Entradas\n" +
                            "3. Relatório de Saídas"
                        );

                        if (relatorioOp != null) {
                            String resultadoRelatorio = "";
                            if (relatorioOp.equals("1")) {
                                resultadoRelatorio = estoque.relatorioMovimentacao();
                            } else if (relatorioOp.equals("2")) {
                                resultadoRelatorio = estoque.relatorioMovimentacaoEntrada();
                            } else if (relatorioOp.equals("3")) {
                                resultadoRelatorio = estoque.relatorioMovimentacaoSaida();
                            }
                            
                            if (!resultadoRelatorio.isEmpty()) {
                                JOptionPane.showMessageDialog(null, resultadoRelatorio, "Relatório de Estoque", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        break;

                    case 6:
                        executando = false;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida!", "Erro", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um valor numérico válido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Aviso / Erro de Regra de Negócio:\n" + e.getMessage(), "Validação", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        JOptionPane.showMessageDialog(null, "Aplicação encerrada. Até breve!");
    }
}