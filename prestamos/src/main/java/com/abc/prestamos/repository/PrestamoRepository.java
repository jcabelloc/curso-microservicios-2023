package com.abc.prestamos.repository;
import com.abc.prestamos.entity.Prestamo;
import com.abc.prestamos.model.PrestamoDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
  @Query("SELECT pr "
      + "   FROM Prestamo pr"
  )
  List<PrestamoDto> getAll();
  @Query("SELECT "
      + "    pr.nroPrestamo AS nroPrestamo,"
      + "    pr.montoDesembolso AS montoDesembolso,"
      + "    pr.tea AS tea, "
      + "    pr.nroCuotas AS nroCuotas,"
      + "    pr.frecuencia  AS frecuencia"
      + "   FROM Prestamo pr"
  )
  List<PrestamoDto> getAll1();
  Optional<PrestamoDto> findDtoByNroPrestamo(Integer nroPrestamo);
}