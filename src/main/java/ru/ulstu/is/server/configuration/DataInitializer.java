package ru.ulstu.is.server.configuration;

import org.springframework.stereotype.Component;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import jakarta.annotation.PostConstruct;
import ru.ulstu.is.server.api.animal.AnimalRq;
import ru.ulstu.is.server.api.diet.DietRq;
import ru.ulstu.is.server.api.habitat.HabitatRq;
import ru.ulstu.is.server.api.ticketorder.TicketOrderRq;
import ru.ulstu.is.server.api.tickettype.TicketTypeRq;
import ru.ulstu.is.server.service.AnimalService;
import ru.ulstu.is.server.service.DietService;
import ru.ulstu.is.server.service.HabitatService;
import ru.ulstu.is.server.service.TicketOrderService;
import ru.ulstu.is.server.service.TicketTypeService;

@Component
@ConditionalOnProperty(name = "vzoo.seed-data", havingValue = "true", matchIfMissing = true)
public class DataInitializer {
    private final HabitatService habitatService;
    private final DietService dietService;
    private final AnimalService animalService;
    private final TicketTypeService ticketTypeService;
    private final TicketOrderService ticketOrderService;

    public DataInitializer(
            HabitatService habitatService,
            DietService dietService,
            AnimalService animalService,
            TicketTypeService ticketTypeService,
            TicketOrderService ticketOrderService) {
        this.habitatService = habitatService;
        this.dietService = dietService;
        this.animalService = animalService;
        this.ticketTypeService = ticketTypeService;
        this.ticketOrderService = ticketOrderService;
    }

    @PostConstruct
    public void init() {
        if (!habitatService.getAll().isEmpty()) {
            return;
        }

        final var h1 = habitatService.create(new HabitatRq("Вольер редких животных", "320", "Редкий"));
        final var h2 = habitatService.create(new HabitatRq("Лесная зона", "440", "Умеренный"));
        final var h3 = habitatService.create(new HabitatRq("Тропический павильон", "210", "Тропический"));
        habitatService.create(new HabitatRq("Водная зона", "100", "Водный"));

        final var d1 = dietService.create(new DietRq("Насекомые и водные беспозвоночные"));
        final var d2 = dietService.create(new DietRq("Фрукты, ягоды и небольшие животные"));
        final var d3 = dietService.create(new DietRq("Растительная пища"));
        dietService.create(new DietRq("Рыба и морепродукты"));

        animalService.create(new AnimalRq(
                "Выхухоль",
                "Редкое животное, занесённое в Красную книгу. Хорошо плавает и живёт рядом с водой.",
                "/images/animal1.png", h1.id(), d1.id()));
        animalService.create(new AnimalRq(
                "Енот",
                "Любопытный и дружелюбный зверёк, который любит фрукты и активные игры.",
                "/images/animal2.jpg", h2.id(), d2.id()));
        animalService.create(new AnimalRq(
                "Горилла",
                "Умное и сильное животное. В онлайн-зоопарке можно наблюдать за его повседневными привычками.",
                "/images/animal3.jpg", h3.id(), d3.id()));

        final var child = ticketTypeService.create(new TicketTypeRq("Детский", 300, "Для детей от 7 до 17 лет"));
        final var adult = ticketTypeService.create(new TicketTypeRq("Взрослый", 600, "Для посетителей от 18 лет"));
        ticketTypeService.create(new TicketTypeRq("Льготный", 400, "Для пенсионеров и льготных категорий"));

        ticketOrderService.create(new TicketOrderRq(
                "иван", "ivanrotov2020@gmail.com", child.id(), 1,
                "2026-05-20", 300, "2026-05-19T12:07:07.741Z"));
        ticketOrderService.create(new TicketOrderRq(
                "иван", "ivanrotov2020@gmail.com", adult.id(), 1,
                "2026-05-21", 600, "2026-05-19T13:51:50.002Z"));
    }
}
