package net.amygdalum.gildedroseweb;

import com.gildedrose.GildedRoseFacade;
import com.gildedrose.Item;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.templ.HandlebarsTemplateEngine;
import io.vertx.ext.web.templ.TemplateEngine;

public class Server extends AbstractVerticle {

	private GildedRoseFacade facade;
	private TemplateEngine engine;

	public Server() {
		facade = new GildedRoseFacade();
		engine = HandlebarsTemplateEngine.create().setExtension("html");
	}

	public void start() {
		Router router = Router.router(vertx);
		router.route("/init").handler(this::init);
		router.route("/add/:name/:sellIn/:quality").handler(this::add);
		router.route("/update/:days").handler(this::update);
		router.route("/remove/:index").handler(this::remove);
		router.route().handler(this::show);

		HttpServer server = vertx.createHttpServer();
		server.requestHandler(router::accept).listen(8080);
	}

	public void init(RoutingContext context) {
		try {
			facade.setItems(new Item[]{
				new Item("+5 Dexterity Vest", 10, 20),	
				new Item("Aged Brie", 2, 0),	
				new Item("Elixir of the Mongoose", 5, 7),	
				new Item("Sulfuras, Hand of Ragnaros", 0, 80),	
				new Item("Sulfuras, Hand of Ragnaros", -1, 80),	
				new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),	
				new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),	
				new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),	
				new Item("Conjured Mana Cake", 3, 6)
			});

			context.next();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void add(RoutingContext context) {
		try {
			String name = context.request().getParam("name");
			int sellIn = Integer.parseInt(context.request().getParam("sellIn"));
			int quality = Integer.parseInt(context.request().getParam("quality"));

			Item item = facade.createItem(name, sellIn, quality);
			facade.addItem(item);

			context.next();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void update(RoutingContext context) {
		try {
			int days = Integer.parseInt(context.request().getParam("days"));
			for (int i = 0; i < days; i++) {
				facade.updateQuality();
			}

			context.next();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(RoutingContext context) {
		try {
			int index = Integer.parseInt(context.request().getParam("index"));

			facade.removeItem(index);

			context.next();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void show(RoutingContext context) {
		context.data().put("items", facade.getItems());
		engine.render(context, "src/main/resources/index.html", res -> {
			if (res.succeeded()) {
				context.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/html").end(res.result());
			} else {
				context.fail(res.cause());
			}
		});
	}

}
