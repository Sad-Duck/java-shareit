package ru.practicum.shareit.booking.mapper;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.booking.dto.BookingDtoIn;
import ru.practicum.shareit.booking.dto.BookingDtoOut;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.practicum.shareit.booking.mapper.BookingMapper.*;

public class BookingMapperTest {

    private static final LocalDateTime START = LocalDateTime.of(2023, 2, 3, 9, 0);

    private static final LocalDateTime END = LocalDateTime.of(2033, 2, 4, 9, 0);

    User owner = new User(1L, "name", "email@mail.ru");

    User booker = new User(2L, "secondName", "secondEmail@mail.ru");

    Item item = new Item(1L, "name", "description", true, owner);

    @Test
    public void toBookingDtoOutTest() {
        Booking booking = new Booking(
                1L,
                START,
                END,
                item,
                booker,
                Status.WAITING
        );

        BookingDtoOut bookingDtoOut = toBookingDtoOut(booking);

        assertNotNull(bookingDtoOut);
        assertEquals(1, bookingDtoOut.getId());
        assertEquals(START, bookingDtoOut.getStart());
        assertEquals(END, bookingDtoOut.getEnd());
        assertEquals(item.getId(), bookingDtoOut.getItem().getId());
        assertEquals(booker.getId(), bookingDtoOut.getBooker().getId());
        assertEquals(Status.WAITING, bookingDtoOut.getStatus());
    }

    @Test
    public void toBookingTest() {
        BookingDtoIn bookingDtoIn = new BookingDtoIn(null, START, END, null);

        Booking booking = toBooking(bookingDtoIn);

        assertNotNull(booking);
        assertEquals(START, booking.getStart());
        assertEquals(END, booking.getEnd());
    }
}