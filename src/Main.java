import database.DatabaseCharacters;
import decorators.DecoratorArma;
import decorators.DecoratorArmadura;
import decorators.DecoratorHabilidadeEspecial;
import decorators.DecoratorMagia;
import personagens.Personagem;
import personagens.PersonagemBase;

import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    private static Scanner scanner = new Scanner(System.in);
    private static DatabaseCharacters bancoDeDados;

    public static void main(String[] args) {
        bancoDeDados = DatabaseCharacters.getInstance();

        System.out.println("╔══════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                                                      ║");
        System.out.println("║                                                                                                      ║");
        System.out.println("║   ██████╗  ██████╗  ██████╗             ██████╗  ██╗   ██╗ ██╗ ██╗      ██████╗  ███████╗ ██████╗    ║");
        System.out.println("║   ██╔══██╗ ██╔══██╗ ██╔════╝            ██╔══██╗ ██║   ██║ ██║ ██║      ██╔══██╗ ██╔════╝ ██╔══██╗   ║");
        System.out.println("║   ██████╔╝ ██████╔╝ ██║  ███╗           ██████╔╝ ██║   ██║ ██║ ██║      ██║  ██║ █████╗   ██████╔╝   ║");
        System.out.println("║   ██╔══██╗ ██╔═══╝  ██║   ██║           ██╔══██╗ ██║   ██║ ██║ ██║      ██║  ██║ ██╔══╝   ██╔══██╗   ║");
        System.out.println("║   ██║  ██║ ██║      ╚██████╔╝           ██████╔╝ ╚██████╔╝ ██║ ███████╗ ██████╔╝ ███████╗ ██║  ██║   ║");
        System.out.println("║   ╚═╝  ╚═╝ ╚═╝       ╚═════╝            ╚═════╝   ╚═════╝  ╚═╝ ╚══════╝ ╚═════╝  ╚══════╝ ╚═╝  ╚═╝   ║");
        System.out.println("║                                                                                                      ║");
        System.out.println("║                                                                                                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════════════════════╝\n");

        boolean executando = true;

        while (executando) {
            exibirMenuPrincipal();
            int opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    criarEGerenciarPersonagem();
                    break;
                case 2:
                    gerenciarBancoDeDados();
                    break;
                case 3:
                    visualizarPersonagens();
                    break;
                case 4:
                    compararPersonagens();
                    break;
                case 5:
                    simularCombateComPersonagemSalvo();
                    break;
                case 6:
                    System.out.println("\n✨ OBRIGADO POR USAR O RPG CHARACTER BUILDER! ✨");
                    System.out.println("Personagens salvos: " + bancoDeDados.getTotalPersonagens());
                    executando = false;
                    break;
                default:
                    System.out.println("\n⚠️  Opção inválida!");
            }

            if (executando) {
                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("═".repeat(70));
        System.out.println("📜 MENU PRINCIPAL - Personagens salvos: " + bancoDeDados.getTotalPersonagens());
        System.out.println("═".repeat(70));
        System.out.println("1. 🆕 Criar e gerenciar um novo personagem");
        System.out.println("2. 💾 Gerenciar banco de dados de personagens");
        System.out.println("3. 👁️  Visualizar todos os personagens salvos");
        System.out.println("4. ⚖️  Comparar dois personagens");
        System.out.println("5. ⚔️  Simular combate com personagem salvo");
        System.out.println("6. 🚪 Sair do programa");
        System.out.println("═".repeat(70));
    }

    private static void criarEGerenciarPersonagem() {
        System.out.println("\n" + "🎭".repeat(35));
        System.out.println("CRIAÇÃO E GERENCIAMENTO DE PERSONAGEM");
        System.out.println("🎭".repeat(35));

        // Opção 1: Criar novo personagem
        System.out.println("\n1. Criar um NOVO personagem");
        System.out.println("2. Editar um personagem EXISTENTE");
        System.out.println("3. Voltar ao menu principal");

        int escolha = lerInteiro("\nEscolha: ");

        switch (escolha) {
            case 1:
                Personagem novoPersonagem = criarPersonagem();
                if (novoPersonagem != null) {
                    System.out.print("\n💾 Deseja salvar este personagem no banco de dados? (S/N): ");
                    String salvar = scanner.nextLine().toUpperCase();
                    if (salvar.equals("S") || salvar.equals("SIM")) {
                        bancoDeDados.adicionarPersonagem(novoPersonagem);
                    }
                }
                break;

            case 2:
                if (bancoDeDados.getTotalPersonagens() == 0) {
                    System.out.println("\n📭 Nenhum personagem salvo para editar!");
                    return;
                }

                bancoDeDados.mostrarTodosPersonagens();
                int indice = lerInteiro("\nDigite o número do personagem para editar: ") - 1;

                if (indice >= 0 && indice < bancoDeDados.getTotalPersonagens()) {
                    Personagem personagem = bancoDeDados.getPersonagemPorIndice(indice);
                    System.out.println("\nEditando personagem:");
                    personagem.mostrarStatus();

                    Personagem editado = equiparPersonagem(personagem);
                    // Atualizar no banco de dados
                    // Como Personagem é imutável com Decorators, precisamos substituir
                    bancoDeDados.removerPersonagem(indice);
                    bancoDeDados.adicionarPersonagem(editado);
                    System.out.println("✅ Personagem atualizado no banco de dados!");
                } else {
                    System.out.println("⚠️  Índice inválido!");
                }
                break;

            case 3:
                return;

            default:
                System.out.println("⚠️  Opção inválida!");
        }
    }

    private static void gerenciarBancoDeDados() {
        System.out.println("\n" + "💾".repeat(35));
        System.out.println("GERENCIAMENTO DO BANCO DE DADOS");
        System.out.println("💾".repeat(35));

        System.out.println("\n1. 📋 Listar todos os personagens");
        System.out.println("2. 🔍 Ver detalhes de um personagem");
        System.out.println("3. 🗑️  Remover um personagem");
        System.out.println("4. 🧹 Limpar todo o banco de dados");
        System.out.println("5. 💾 Exportar personagens (simulação)");
        System.out.println("6. ↩️  Voltar ao menu principal");

        int escolha = lerInteiro("\nEscolha: ");

        switch (escolha) {
            case 1:
                bancoDeDados.mostrarTodosPersonagens();
                break;

            case 2:
                if (bancoDeDados.getTotalPersonagens() == 0) {
                    System.out.println("\n📭 Nenhum personagem salvo!");
                    return;
                }

                bancoDeDados.mostrarTodosPersonagens();
                int indice = lerInteiro("\nDigite o número do personagem para ver detalhes: ") - 1;

                if (indice >= 0 && indice < bancoDeDados.getTotalPersonagens()) {
                    Personagem p = bancoDeDados.getPersonagemPorIndice(indice);
                    System.out.println("\n" + "📊".repeat(30));
                    System.out.println("DETALHES DO PERSONAGEM #" + (indice + 1));
                    System.out.println("📊".repeat(30));
                    p.mostrarStatus();
                } else {
                    System.out.println("⚠️  Índice inválido!");
                }
                break;

            case 3:
                if (bancoDeDados.getTotalPersonagens() == 0) {
                    System.out.println("\n📭 Nenhum personagem para remover!");
                    return;
                }

                bancoDeDados.mostrarTodosPersonagens();
                int indiceRemover = lerInteiro("\nDigite o número do personagem para remover: ") - 1;

                if (indiceRemover >= 0 && indiceRemover < bancoDeDados.getTotalPersonagens()) {
                    System.out.print("⚠️  Tem certeza que deseja remover este personagem? (S/N): ");
                    String confirmacao = scanner.nextLine().toUpperCase();
                    if (confirmacao.equals("S") || confirmacao.equals("SIM")) {
                        bancoDeDados.removerPersonagem(indiceRemover);
                    } else {
                        System.out.println("Operação cancelada.");
                    }
                } else {
                    System.out.println("⚠️  Índice inválido!");
                }
                break;

            case 4:
                System.out.print("⚠️  ⚠️  TEM CERTEZA ABSOLUTA? Isso apagará TODOS os personagens! (S/N): ");
                String confirmacao = scanner.nextLine().toUpperCase();
                if (confirmacao.equals("S") || confirmacao.equals("SIM")) {
                    bancoDeDados.limparBancoDeDados();
                } else {
                    System.out.println("Operação cancelada.");
                }
                break;

            case 5:
                System.out.println("\n💾 Exportando personagens...");
                System.out.println("Total de personagens: " + bancoDeDados.getTotalPersonagens());
                System.out.println("(Simulação - em um sistema real, salvaríamos em arquivo)");
                break;

            case 6:
                return;

            default:
                System.out.println("⚠️  Opção inválida!");
        }
    }

    private static void visualizarPersonagens() {
        if (bancoDeDados.getTotalPersonagens() == 0) {
            System.out.println("\n📭 Nenhum personagem salvo no banco de dados!");
            return;
        }

        System.out.println("\n" + "👁️".repeat(35));
        System.out.println("VISUALIZAÇÃO DE PERSONAGENS");
        System.out.println("👁️".repeat(35));

        System.out.println("\nEscolha o modo de visualização:");
        System.out.println("1. 📋 Lista resumida");
        System.out.println("2. 📊 Lista detalhada");
        System.out.println("3. 📈 Estatísticas totais");

        int escolha = lerInteiro("\nEscolha: ");

        switch (escolha) {
            case 1:
                bancoDeDados.mostrarTodosPersonagens();
                break;

            case 2:
                List<Personagem> todos = bancoDeDados.getTodosPersonagens();
                System.out.println("\n" + "📊".repeat(30));
                System.out.println("LISTA DETALHADA DE PERSONAGENS");
                System.out.println("📊".repeat(30));

                for (int i = 0; i < todos.size(); i++) {
                    System.out.println("\n" + "═".repeat(60));
                    System.out.println("PERSONAGEM #" + (i + 1));
                    System.out.println("═".repeat(60));
                    todos.get(i).mostrarStatus();
                }
                break;

            case 3:
                mostrarEstatisticasGerais();
                break;

            default:
                System.out.println("⚠️  Opção inválida!");
        }
    }

    private static void mostrarEstatisticasGerais() {
        List<Personagem> todos = bancoDeDados.getTodosPersonagens();

        if (todos.isEmpty()) {
            System.out.println("\n📭 Nenhum personagem para mostrar estatísticas!");
            return;
        }

        System.out.println("\n" + "📈".repeat(35));
        System.out.println("ESTATÍSTICAS GERAIS DO BANCO DE DADOS");
        System.out.println("📈".repeat(35));

        double somaAtaque = 0, somaDefesa = 0, somaVida = 0, somaVelocidade = 0;
        double maxAtaque = Double.MIN_VALUE, minAtaque = Double.MAX_VALUE;
        Personagem maisForte = null;
        double maiorPoderTotal = 0;

        for (Personagem p : todos) {
            double ataque = p.getPoderAtaque();
            double defesa = p.getPoderDefesa();
            double vida = p.getVida();
            double velocidade = p.getVelocidade();
            double poderTotal = ataque + defesa + vida;

            somaAtaque += ataque;
            somaDefesa += defesa;
            somaVida += vida;
            somaVelocidade += velocidade;

            if (ataque > maxAtaque) maxAtaque = ataque;
            if (ataque < minAtaque) minAtaque = ataque;

            if (poderTotal > maiorPoderTotal) {
                maiorPoderTotal = poderTotal;
                maisForte = p;
            }
        }

        int total = todos.size();

        System.out.println("\n📊 RESUMO ESTATÍSTICO:");
        System.out.println("═".repeat(40));
        System.out.printf("Total de personagens: %d%n", total);
        System.out.printf("Ataque médio: %.2f%n", somaAtaque / total);
        System.out.printf("Defesa média: %.2f%n", somaDefesa / total);
        System.out.printf("Vida média: %.2f%n", somaVida / total);
        System.out.printf("Velocidade média: %.2f%n", somaVelocidade / total);
        System.out.printf("Maior ataque: %.2f%n", maxAtaque);
        System.out.printf("Menor ataque: %.2f%n", minAtaque);

        System.out.println("\n🏆 PERSONAGEM MAIS FORTE:");
        System.out.println("═".repeat(40));
        if (maisForte != null) {
            System.out.println(maisForte.getDescrisao());
            System.out.printf("Poder total: %.2f%n", maiorPoderTotal);
        }
    }

    private static void compararPersonagens()
    {
        if (bancoDeDados.getTotalPersonagens() < 2) {
            System.out.println("\n⚠️  É necessário ter pelo menos 2 personagens para comparar!");
            return;
        }

        bancoDeDados.mostrarTodosPersonagens();

        System.out.println("\nSelecione dois personagens para comparar:");
        int indice1 = lerInteiro("Primeiro personagem: ") - 1;
        int indice2 = lerInteiro("Segundo personagem: ") - 1;

        bancoDeDados.compararPersonagens(indice1, indice2);
    }

    private static void simularCombateComPersonagemSalvo() {
        if (bancoDeDados.getTotalPersonagens() == 0) {
            System.out.println("\n⚠️  Nenhum personagem salvo para combater!");
            return;
        }

        System.out.println("\n" + "⚔️".repeat(35));
        System.out.println("SIMULAÇÃO DE COMBATE");
        System.out.println("⚔️".repeat(35));

        System.out.println("\nEscolha um modo:");
        System.out.println("1. Personagem salvo vs Inimigo aleatório");
        System.out.println("2. Personagem salvo vs Personagem salvo");

        int modo = lerInteiro("\nEscolha o modo: ");

        switch (modo) {
            case 1:
                bancoDeDados.mostrarTodosPersonagens();
                int indice = lerInteiro("\nEscolha seu personagem: ") - 1;

                if (indice >= 0 && indice < bancoDeDados.getTotalPersonagens()) {
                    Personagem jogador = bancoDeDados.getPersonagemPorIndice(indice);
                    simularCombate(jogador);
                } else {
                    System.out.println("⚠️  Índice inválido!");
                }
                break;

            case 2:
                if (bancoDeDados.getTotalPersonagens() < 2) {
                    System.out.println("\n⚠️  É necessário ter pelo menos 2 personagens salvos!");
                    return;
                }

                bancoDeDados.mostrarTodosPersonagens();
                System.out.println("\nSelecione dois personagens para combater:");
                int indice1 = lerInteiro("Primeiro personagem: ") - 1;
                int indice2 = lerInteiro("Segundo personagem: ") - 1;

                if (indice1 >= 0 && indice1 < bancoDeDados.getTotalPersonagens() &&
                        indice2 >= 0 && indice2 < bancoDeDados.getTotalPersonagens()) {

                    Personagem p1 = bancoDeDados.getPersonagemPorIndice(indice1);
                    Personagem p2 = bancoDeDados.getPersonagemPorIndice(indice2);

                    System.out.println("\n" + "⚔️".repeat(40));
                    System.out.println("COMBATE ENTRE PERSONAGENS SALVOS!");
                    System.out.println("⚔️".repeat(40));

                    System.out.println("\nPERSONAGEM 1:");
                    p1.mostrarStatus();

                    System.out.println("\nPERSONAGEM 2:");
                    p2.mostrarStatus();

                    // Determinar vencedor
                    double poder1 = p1.getPoderAtaque() + p1.getPoderDefesa() + p1.getVida();
                    double poder2 = p2.getPoderAtaque() + p2.getPoderDefesa() + p2.getVida();

                    System.out.println("\n" + "🏆 RESULTADO DO COMBATE:");
                    System.out.println("═".repeat(40));
                    System.out.println("Poder total Personagem 1: " + poder1);
                    System.out.println("Poder total Personagem 2: " + poder2);

                    if (poder1 > poder2) {
                        System.out.println("🎉 VENCEDOR: Personagem 1!");
                    } else if (poder2 > poder1) {
                        System.out.println("🎉 VENCEDOR: Personagem 2!");
                    } else {
                        System.out.println("🤝 EMPATE!");
                    }
                } else {
                    System.out.println("⚠️  Índices inválidos!");
                }
                break;

            default:
                System.out.println("⚠️  Modo inválido!");
        }
    }

    // Métodos auxiliares (os mesmos da versão anterior)
    private static Personagem criarPersonagem() {
        // ... (código do método criarPersonagem da versão anterior)
        // Implementação idêntica à anterior
        System.out.println("\n" + "✦".repeat(60));
        System.out.println("CRIAÇÃO DE PERSONAGEM");
        System.out.println("✦".repeat(60));

        System.out.print("📝 Digite o nome do seu personagem: ");
        String nome = scanner.nextLine();

        System.out.println("\n🏹 ESCOLHA SUA CLASSE:");
        PersonagemBase.Classe[] classes = PersonagemBase.Classe.values();
        for (int i = 0; i < classes.length; i++) {
            System.out.printf("%d. %s%n", i + 1, formatarNomeClasse(classes[i].toString()));
        }

        int escolhaClasse;
        do {
            escolhaClasse = lerInteiro("\nEscolha a classe (1-" + classes.length + "): ");
        } while (escolhaClasse < 1 || escolhaClasse > classes.length);

        PersonagemBase.Classe classeEscolhida = classes[escolhaClasse - 1];
        Personagem personagem = new PersonagemBase(nome, classeEscolhida);

        System.out.println("\n✨ Personagem base criado!");
        personagem.mostrarStatus();

        System.out.print("\nDeseja equipar seu personagem agora? (S/N): ");
        String resposta = scanner.nextLine().toUpperCase();

        if (resposta.equals("S") || resposta.equals("SIM")) {
            return equiparPersonagem(personagem);
        }

        return personagem;
    }

    private static Personagem equiparPersonagem(Personagem personagem) {
        // ... (código do método equiparPersonagem da versão anterior)
        // Implementação idêntica à anterior
        boolean equipando = true;

        while (equipando) {
            System.out.println("\n" + "⚔️".repeat(30));
            System.out.println("MENU DE EQUIPAMENTOS");
            System.out.println("⚔️".repeat(30));
            System.out.println("1. 🛡️  Equipar Armadura");
            System.out.println("2. ⚔️  Equipar Arma");
            System.out.println("3. 🔮 Aprender Magia");
            System.out.println("4. 💫 Aprender Habilidade Especial");
            System.out.println("5. ✅ Finalizar equipamento");
            System.out.println("⚔️".repeat(30));

            int opcao = lerInteiro("Escolha: ");

            switch (opcao) {
                case 1:
                    System.out.println("\n🛡️  TIPOS DE ARMADURA:");
                    System.out.println("1. Leve   (+10 defesa, -2 velocidade)");
                    System.out.println("2. Média  (+15 defesa, -3 velocidade)");
                    System.out.println("3. Pesada (+20 defesa, -6 velocidade)");

                    int escolhaArmadura = lerInteiro("Escolha (1-3): ");
                    String tipoArmadura = switch (escolhaArmadura) {
                        case 1 -> "leve";
                        case 2 -> "media";
                        case 3 -> "pesada";
                        default -> null;
                    };

                    if (tipoArmadura != null) {
                        personagem = new DecoratorArmadura(personagem, tipoArmadura);
                        System.out.println("✅ Armadura equipada!");
                    }
                    break;

                case 2:
                    System.out.println("\n⚔️  ARMAS DISPONÍVEIS:");
                    System.out.println("1. Espada  (+10 ataque, -2 velocidade)");
                    System.out.println("2. Arco    (+15 ataque, -3 velocidade)");
                    System.out.println("3. Adaga  (+6 ataque)");
                    System.out.println("4. Cajado  (+20 ataque, -3 velocidade)");
                    System.out.println("5. Machado (+25 ataque, -4 velocidade)");

                    int escolhaArma = lerInteiro("Escolha (1-5): ");
                    String tipoArma = switch (escolhaArma) {
                        case 1 -> "espada";
                        case 2 -> "arco";
                        case 3 -> "adaga";
                        case 4 -> "cajado";
                        case 5 -> "machado";
                        default -> null;
                    };

                    if (tipoArma != null) {
                        personagem = new DecoratorArma(personagem, tipoArma);
                        System.out.println("✅ Arma equipada!");
                    }
                    break;

                case 3:
                    System.out.println("\n🔮 MAGIAS DISPONÍVEIS:");
                    System.out.println("1. Fogo  (+30 ataque, -10 vida)");
                    System.out.println("2. Gelo  (+20 ataque)");
                    System.out.println("3. Cura  (+10 ataque, +50 vida)");

                    int escolhaMagia = lerInteiro("Escolha (1-3): ");
                    String tipoMagia = switch (escolhaMagia) {
                        case 1 -> "fogo";
                        case 2 -> "gelo";
                        case 3 -> "cura";
                        default -> null;
                    };

                    if (tipoMagia != null) {
                        personagem = new DecoratorMagia(personagem, tipoMagia);
                        System.out.println("✅ Magia aprendida!");
                    }
                    break;

                case 4:
                    System.out.println("\n💫 HABILIDADES ESPECIAIS:");
                    DecoratorHabilidadeEspecial.HabilidadeEspecial[] habilidades = DecoratorHabilidadeEspecial.HabilidadeEspecial.values();
                    for (int i = 0; i < habilidades.length; i++) {
                        System.out.printf("%d. %s%n", i + 1, habilidades[i].getNomeHabilidade());
                    }

                    int escolhaHabilidade = lerInteiro("Escolha (1-" + habilidades.length + "): ");

                    if (escolhaHabilidade >= 1 && escolhaHabilidade <= habilidades.length) {
                        personagem = new DecoratorHabilidadeEspecial(personagem,
                                habilidades[escolhaHabilidade - 1]);
                        System.out.println("✅ Habilidade aprendida!");
                    }
                    break;

                case 5:
                    System.out.println("\n✨ Personagem equipado com sucesso!");
                    personagem.mostrarStatus();
                    equipando = false;
                    break;

                default:
                    System.out.println("⚠️  Opção inválida!");
            }
        }

        return personagem;
    }

    private static void simularCombate(Personagem jogador) {
        // ... (código do método simularCombate da versão anterior)
        // Implementação idêntica à anterior
        PersonagemBase.Classe classeInimigo = PersonagemBase.Classe.values()[
                (int)(Math.random() * PersonagemBase.Classe.values().length)];
        Personagem inimigo = new PersonagemBase("Inimigo " + classeInimigo, classeInimigo);

        if (Math.random() > 0.5) {
            inimigo = new DecoratorArma(inimigo, "espada");
        }
        if (Math.random() > 0.3) {
            inimigo = new DecoratorArmadura(inimigo, "media");
        }

        System.out.println("\n⚔️  INIMIGO ENCONTRADO!");
        inimigo.mostrarStatus();

        double poderJogador = jogador.getPoderAtaque() + jogador.getPoderDefesa() + jogador.getVida();
        double poderInimigo = inimigo.getPoderAtaque() + inimigo.getPoderDefesa() + inimigo.getVida();

        System.out.println("\n🎯 ANÁLISE DE COMBATE:");
        System.out.println("Seu poder total: " + poderJogador);
        System.out.println("Poder do inimigo: " + poderInimigo);

        if (poderJogador > poderInimigo * 1.5) {
            System.out.println("\n✅ VITÓRIA FÁCIL!");
        } else if (poderJogador > poderInimigo) {
            System.out.println("\n⚔️  VITÓRIA!");
        } else if (poderJogador * 1.5 < poderInimigo) {
            System.out.println("\n💀 DERROTA!");
        } else {
            System.out.println("\n🤝 EMPATE!");
        }
    }

    private static String formatarNomeClasse(String nomeClasse) {
        return nomeClasse.substring(0, 1) + nomeClasse.substring(1).toLowerCase();
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️  Digite um número válido!");
            }
        }
    }
}