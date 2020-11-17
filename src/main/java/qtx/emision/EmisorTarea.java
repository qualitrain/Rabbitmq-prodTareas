package qtx.emision;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class EmisorTarea {
	private String nombreColaTareas;
	private String hostRabbitMQ;
	
	public EmisorTarea(String nombreColaTareas, String hostRabbitMQ) {
		super();
		this.nombreColaTareas = nombreColaTareas;
		this.hostRabbitMQ = hostRabbitMQ;
	}
	public EmisorTarea() {
		super();
		this.nombreColaTareas = "colaTareas01";
		this.hostRabbitMQ = "localhost";
	}
	public void generarNuevaTarea(String[] palabrasMensaje) {
		String mensaje = String.join(" ", palabrasMensaje);
		this.generarNuevaTarea(mensaje);
	}
	public void generarNuevaTarea(String mensaje) {
		ConnectionFactory fabricaConexiones = new ConnectionFactory();
		fabricaConexiones.setHost(this.hostRabbitMQ);
		try(Connection conexion = fabricaConexiones.newConnection();
			Channel canal = conexion.createChannel()	){
			
			//*** queueDeclare(queue, durable, exclusive, autoDelete, arguments)
			//*** basicPublish(exchange, routingKey, props, body)
			canal.queueDeclare(this.nombreColaTareas, true, false, false, null);
			canal.basicPublish("", this.nombreColaTareas, 
					          MessageProperties.PERSISTENT_TEXT_PLAIN, mensaje.getBytes("UTF-8"));
			System.out.println("Mensaje enviado: " + mensaje);
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
	}

}
