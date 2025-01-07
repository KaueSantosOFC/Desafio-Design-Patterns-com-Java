package com.cafeteria.cafedokaue.state;

import com.cafeteria.cafedokaue.model.Pedido;


public enum EstadoPedido {
    NOVO_PEDIDO {
        @Override
        public void aprovar(Pedido pedido) {
            pedido.setEstadoAtual(PREPARACAO);
            System.out.println("Pedido em preparação!");
        }

        @Override
        public void rejeitar(Pedido pedido) {
            pedido.setEstadoAtual(REJEITADO);
            System.out.println("Pedido rejeitado!");
        }

        @Override
        public void cancelar(Pedido pedido) {
            pedido.setEstadoAtual(CANCELADO);
            System.out.println("Pedido cancelado!");
        }
    },
    PREPARACAO {
        @Override
        public void aprovar(Pedido pedido) {
            pedido.setEstadoAtual(PRONTO_PARA_RETIRAR);
            System.out.println("Pedido pronto para a Retirada!");
        }

        @Override
        public void cancelar(Pedido pedido) {
            pedido.setEstadoAtual(CANCELADO);
            System.out.println("Pedido cancelado!");
        }
    },
    PRONTO_PARA_RETIRAR {
        @Override
        public void aprovar(Pedido pedido) {
            pedido.setEstadoAtual(FINALIZADO);
            System.out.println("Pedido Finalizado!");
        }
    },
    FINALIZADO {
        @Override
        public void aprovar(Pedido pedido) {
            System.out.println("Pedido já foi finalizado!");
        }

        @Override
        public void cancelar(Pedido pedido) {
            System.out.println("Pedido já foi finalizado!");
        }
    },
    REJEITADO {
        @Override
        public void cancelar(Pedido pedido) {
            pedido.setEstadoAtual(CANCELADO);
            System.out.println("Pedido cancelado!");
        }
    },
    CANCELADO {
        @Override
        public void aprovar(Pedido pedido) {
            System.out.println("Não é possível aprovar um pedido cancelado.");
        }
    };

    // Métodos abstratos
    public void aprovar(Pedido pedido) {
        throw new UnsupportedOperationException("Ação não suportada neste estado.");
    }

    public void rejeitar(Pedido pedido) {
        throw new UnsupportedOperationException("Ação não suportada neste estado.");
    }

    public void cancelar(Pedido pedido) {
        throw new UnsupportedOperationException("Ação não suportada neste estado.");
    }
}
