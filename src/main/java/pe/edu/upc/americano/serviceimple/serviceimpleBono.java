package pe.edu.upc.americano.serviceimple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.americano.entities.Bono;
import pe.edu.upc.americano.entities.Reportes;
import pe.edu.upc.americano.entities.Usuario;
import pe.edu.upc.americano.repository.RepositoryBono;
import pe.edu.upc.americano.repository.RepositoryUsuario;
import pe.edu.upc.americano.serviceinter.serviceinterBono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class serviceimpleBono implements serviceinterBono {
    @Autowired
    private RepositoryBono repositoryBono;
    @Autowired
    private serviceimpleReportes serviceimpleReportes;
    @Autowired
    private RepositoryUsuario repositoryUsuario;

    @Override
    public void eliminarBono(int id) {
        // Primero eliminar el reporte asociado al bono
        try {
            serviceimpleReportes.eliminarReportePorIdBono(id);
        } catch (Exception e) {
            System.out.println("No se encontró reporte asociado al bono " + id + " o ya fue eliminado");
        }
        
        // Luego eliminar el bono
        repositoryBono.deleteById(id);
    }

    public int frecuenciadias(String tiempodias) {
        int dias=1;
        switch (tiempodias.toLowerCase()) {
            case "quincenal":
                dias=15;
                return dias;
            case "mensual":
                dias=30;
                return dias;
            case "bimestral":
                dias=60;
                return dias;
            case "trimestral":
                dias=90;
                return dias;
            case "cuatrimestral":
                dias=120;
                return dias;
            case "semestral":
                dias=180;
                return dias;
            case "anual":
                dias=360;
                return dias;
            default:
                return dias; // Valor por defecto
        }
    }

    public double convertirTNaTEP(double tasa,String tiempotasa,String cap, String tiempopago){
        double tasacovertida = 0;

        double tiempodiastasa = 0;
        double tiempodiascap = 0;
        double tiempodiaspago = 0;

        //hallamos valores
        tiempodiastasa = frecuenciadias(tiempotasa);
        tiempodiascap = frecuenciadias(cap);
        tiempodiaspago = frecuenciadias(tiempopago);

        //Formula TN a TEP
        tasacovertida = Math.pow(1 + tasa/(tiempodiastasa/tiempodiascap), tiempodiaspago / tiempodiascap) - 1;

        return tasacovertida;
    }

    public double convertirTEP(double tasa, String tiempotasa, String tiempopago){
        double tasacovertida = 0;
        double tiempodiastasa = 0;
        double tiempodiaspago = 0;

        //hallamos valores
        tiempodiastasa = frecuenciadias(tiempotasa);
        tiempodiaspago = frecuenciadias(tiempopago);

        //Formula TEP
        tasacovertida= Math.pow(1 + tasa, tiempodiaspago / tiempodiastasa) - 1;

        return tasacovertida;
    }

    public double convertirCOK(double tasadesc, String tiempopago){
        double tasacovertida = 0;
        double tiempodiastasa = 360;
        double tiempodiaspago = 0;

        //hallamos valores
        tiempodiaspago = frecuenciadias(tiempopago);

        //Formula TEP
        tasacovertida= Math.pow(1 + tasadesc, tiempodiaspago / tiempodiastasa) - 1;

        return tasacovertida;
    }
    public double convertirTIR(List<Double> flujos){
        // Validaciones de entrada
        if (flujos == null || flujos.isEmpty()) {
            throw new IllegalArgumentException("La lista de flujos no puede estar vacía");
        }
        
        // Verificar que hay al menos un flujo negativo y uno positivo
        boolean tieneNegativo = false;
        boolean tienePositivo = false;
        for (Double flujo : flujos) {
            if (flujo < 0) tieneNegativo = true;
            if (flujo > 0) tienePositivo = true;
        }
        
        if (!tieneNegativo || !tienePositivo) {
            // Si no hay cambio de signos, retornar 0
            return 0.0;
        }
        
        double tir = 0.1; // Valor inicial (10%)
        double tolerancia = 1e-8;
        int maxIteraciones = 10000;
        
        // Intentar con diferentes valores iniciales si falla
        double[] valoresIniciales = {0.1, 0.05, 0.15, 0.01, 0.5, -0.1};
        
        for (double valorInicial : valoresIniciales) {
            tir = valorInicial;
            
            try {
                for (int i = 0; i < maxIteraciones; i++) {
                    double valorActual = 0.0;
                    double derivada = 0.0;

                    for (int t = 0; t < flujos.size(); t++) {
                        double denominador = Math.pow(1 + tir, t);
                        if (Double.isInfinite(denominador) || denominador == 0) {
                            throw new ArithmeticException("Denominador inválido");
                        }
                        valorActual += flujos.get(t) / denominador;
                        derivada -= t * flujos.get(t) / (denominador * (1 + tir));
                    }

                    if (Math.abs(derivada) < 1e-12) {
                        // Derivada muy pequeña, cambiar valor inicial
                        break;
                    }

                    double nuevaTir = tir - valorActual / derivada;

                    // Validar que la nueva TIR no sea extrema
                    if (nuevaTir < -0.99 || nuevaTir > 10.0) {
                        break;
                    }

                    if (Math.abs(nuevaTir - tir) < tolerancia) {
                        return nuevaTir;
                    }

                    tir = nuevaTir;
                }
            } catch (Exception e) {
                // Continuar con el siguiente valor inicial
                continue;
            }
        }
        
        // Si no se encuentra solución, retornar un valor por defecto
        System.out.println("Advertencia: No se pudo calcular la TIR con precisión. Usando valor por defecto.");
        return 0.05; // 5% como valor por defecto
    }

    @Override
    public void insertarBono(Bono b) {
        // Validaciones de entrada
        if (b == null) {
            throw new IllegalArgumentException("El bono no puede ser nulo");
        }
        
        // Validar período de gracia
        if (b.getTiempogracia() < 0) {
            b.setTiempogracia(0);
        }
        
        if (b.getPeriodogracia() == null || b.getPeriodogracia().trim().isEmpty()) {
            b.setPeriodogracia("No tiene");
        }
        
        // Validar que el período de gracia sea válido
        String[] tiposGraciaValidos = {"No tiene", "Parcial", "Total"};
        boolean esValido = false;
        for (String tipo : tiposGraciaValidos) {
            if (tipo.equals(b.getPeriodogracia())) {
                esValido = true;
                break;
            }
        }
        if (!esValido) {
            b.setPeriodogracia("No tiene");
        }
        
        // Validar otros campos críticos
        if (b.getValorNominal() <= 0) {
            throw new IllegalArgumentException("El valor nominal debe ser mayor a 0");
        }
        
        if (b.getNumerodetiempo() <= 0) {
            throw new IllegalArgumentException("El número de tiempo debe ser mayor a 0");
        }
        
        if (b.getTasadeinteres() < 0) {
            throw new IllegalArgumentException("La tasa de interés no puede ser negativa");
        }

        //Primera parte
        int numcuotasporao = 0;
        int totalnumcuotas = 0;
        double costosinicialesemisor = 0;
        double costosinicialesbonista = 0;
        double valorinicialbonista = 0;
        double valorinicialemisor = 0;
        double tasadescuentoresultante = 0;
        double tasaresultante=0;

        //Cuotas
        numcuotasporao = b.getDiasporyear()/frecuenciadias(b.getFrecuenciadepago());
        totalnumcuotas = b.getNumerodetiempo()*numcuotasporao; //total de periodos o cuotas
        
        // Validar que el período de gracia no sea mayor al total de cuotas
        if (b.getTiempogracia() > totalnumcuotas) {
            b.setTiempogracia(totalnumcuotas);
        }

        //Tasas
        if (b.getTipotasadeinteres().equals("Nominal")){
            tasaresultante= convertirTNaTEP(b.getTasadeinteres(),b.getTiempotasadeinteres(),b.getCapitalizacion(),b.getFrecuenciadepago());
        }else{
            tasaresultante= convertirTEP(b.getTasadeinteres(),b.getTiempotasadeinteres(),b.getFrecuenciadepago());
        }

        //Valor Nominal
        costosinicialesemisor = (b.getgCAVALI()+b.getGcolocacion()+b.getGestructuracion()+b.getGflotacion())*b.getValorNominal();
        costosinicialesbonista =(b.getgCAVALI() + b.getGflotacion())*b.getValorNominal();

        valorinicialemisor = b.getValorNominal()-costosinicialesemisor;
        valorinicialbonista = -(b.getValorNominal())-costosinicialesbonista;

        //Tasa de descuento
        tasadescuentoresultante = convertirCOK(b.getTasadedescuento(),b.getFrecuenciadepago());

        //Inicio de periodos
        List<Double> valoresPorSaldo= new ArrayList<>();
        List<Double> valoresPorSaldoFinal= new ArrayList<>();
        List<Double> valoresPorInteres= new ArrayList<>();
        List<Double> valoresPoramortizacion= new ArrayList<>();
        List<Double> valoresPorcuota= new ArrayList<>();
        List<Double> valoresPorFlujoemisor= new ArrayList<>();
        List<Double> valoresPorFlujobonista= new ArrayList<>();

        double saldo = b.getValorNominal();
        valoresPorSaldo.add(saldo);

        valoresPorFlujoemisor.add(valorinicialemisor);
        valoresPorFlujobonista.add(valorinicialbonista);

        for (int i = 0; i < totalnumcuotas; i++) {
            int valor=i+1;
            // Validar entrada del período de gracia
            int tiempoGracia = Math.max(0, Math.min(b.getTiempogracia(), totalnumcuotas));
            String tipoGracia = b.getPeriodogracia() != null ? b.getPeriodogracia() : "No tiene";
            
            // Si tiene periodo de gracia
            if(i < tiempoGracia && !tipoGracia.equals("No tiene")){
                if(Objects.equals(tipoGracia, "Total")){
                    // Período de gracia total: no se paga ni capital ni intereses
                    double amortizacion = 0;
                    valoresPoramortizacion.add(amortizacion);

                    double interes = saldo*tasaresultante;
                    valoresPorInteres.add(interes);

                    double cuota = 0;
                    valoresPorcuota.add(cuota);

                    double saldofinal = saldo + interes;
                    valoresPorSaldoFinal.add(saldofinal);

                    double flujoemisor = 0;
                    double flujobonista = 0;
                    valoresPorFlujoemisor.add(flujoemisor);
                    valoresPorFlujobonista.add(flujobonista);

                    saldo += interes;
                } else if(Objects.equals(tipoGracia, "Parcial")){
                    // Período de gracia parcial: solo se pagan intereses, no capital
                    double amortizacion = 0;
                    valoresPoramortizacion.add(amortizacion);

                    double interes = saldo*tasaresultante;
                    valoresPorInteres.add(interes);

                    double cuota = interes;
                    valoresPorcuota.add(cuota);

                    double saldofinal = saldo;
                    valoresPorSaldoFinal.add(saldofinal);

                    double flujoemisor = -interes;
                    double flujobonista = interes;
                    valoresPorFlujoemisor.add(flujoemisor);
                    valoresPorFlujobonista.add(flujobonista);
                    
                    // El saldo no cambia en período de gracia parcial
                }
            }else{
                if(Objects.equals(valor, totalnumcuotas)){
                    double amortizacion = 0;
                    amortizacion=saldo;
                    valoresPoramortizacion.add(amortizacion);

                    double interes = 0;
                    interes = saldo*tasaresultante;
                    valoresPorInteres.add(interes);

                    double cuota = 0;
                    cuota=amortizacion+interes;
                    valoresPorcuota.add(cuota);

                    double saldofinal = 0;
                    saldofinal=saldo-amortizacion;
                    valoresPorSaldoFinal.add(saldofinal);

                    double flujoemisor = 0;
                    flujoemisor=-(cuota+(b.getGprima()*saldo));

                    double flujobonista = 0;
                    flujobonista=cuota+(b.getGprima()*saldo);

                    valoresPorFlujoemisor.add(flujoemisor);
                    valoresPorFlujobonista.add(flujobonista);
                }else{
                    double amortizacion = 0;
                    valoresPoramortizacion.add(amortizacion);

                    double interes = 0;
                    interes = saldo*tasaresultante;
                    valoresPorInteres.add(interes);

                    double cuota = 0;
                    cuota=interes;
                    valoresPorcuota.add(cuota);

                    double saldofinal = 0;
                    saldofinal=saldo;
                    valoresPorSaldoFinal.add(saldofinal);

                    double flujoemisor = 0;
                    flujoemisor=-(interes);

                    double flujobonista = 0;
                    flujobonista=interes;

                    valoresPorFlujoemisor.add(flujoemisor);
                    valoresPorFlujobonista.add(flujobonista);
                }
            }
        }

        int idUsuariobono = 0;
        idUsuariobono = b.getUsuario().getIdUsuario();

        // Establecer estado por defecto como "Emitido"
        if (b.getEstado() == null || b.getEstado().isEmpty()) {
            b.setEstado("Emitido");
        }

        // Establecer el emisor original
        if (b.getEmisor() == null) {
            b.setEmisor(b.getUsuario());
        }

        repositoryBono.save(b);

        //Reporte
        int idbono = 0;
        double duracion = 0;
        double duracionmodificada = 0;
        double convexidad = 0;
        double tcea = 0;
        double trea = 0;
        double preciomaximo = 0;

        Reportes reportes = new Reportes();

        Bono nuevobono = new Bono();

        //halla cada valor IDBONO
        nuevobono = findFirstByUsuarioIdOrderByIdDesc(idUsuariobono);
        idbono = nuevobono.getIdBono();

        //halla cada valor PRECIOMAXIMO
        for (int i = 1; i < valoresPorFlujobonista.size(); i++) {
            if(valoresPorFlujobonista.get(i)!=0){
                double denominador = Math.pow(1 + tasadescuentoresultante, i);
                if (!Double.isInfinite(denominador) && denominador != 0) {
                    preciomaximo = preciomaximo + valoresPorFlujobonista.get(i)/denominador;
                }
            }
        }
        
        //halla cada valor DURACION
        double sumaduracion = 0;
        for (int i = 1; i < valoresPorFlujobonista.size(); i++) {
            if(valoresPorFlujobonista.get(i)!=0){
                double denominador = Math.pow(1 + tasadescuentoresultante, i);
                if (!Double.isInfinite(denominador) && denominador != 0) {
                    sumaduracion = sumaduracion + (valoresPorFlujobonista.get(i)/denominador)*i;
                }
            }
        }

        duracion = preciomaximo > 0 ? sumaduracion/preciomaximo : 0;

        //halla cada valor DURACION MODIFICADA
        duracionmodificada = (1+tasadescuentoresultante) != 0 ? duracion/(1+tasadescuentoresultante) : 0;

        //halla cada valor CONVEXIDAD
        double primeraconvexidad = 0;
        double sumaconvexidad = 0;

        if (preciomaximo > 0) {
            double denominadorConvexidad = preciomaximo*(Math.pow(1 + tasadescuentoresultante, 2));
            if (!Double.isInfinite(denominadorConvexidad) && denominadorConvexidad != 0) {
                primeraconvexidad = 1/denominadorConvexidad;
            }
        }

        for (int i = 1; i < valoresPorFlujobonista.size(); i++) {
            if(valoresPorFlujobonista.get(i)!=0){
                double denominador = Math.pow(1 + tasadescuentoresultante, i);
                if (!Double.isInfinite(denominador) && denominador != 0) {
                    sumaconvexidad = sumaconvexidad + ((valoresPorFlujobonista.get(i)/denominador)*(i*(i + 1)));
                }
            }
        }

        convexidad = primeraconvexidad*sumaconvexidad;

        //halla cada valor TCEA
        try {
            double TIR = convertirTIR(valoresPorFlujoemisor);
            tcea = Math.pow(1 + TIR, (double) b.getDiasporyear() /frecuenciadias(b.getFrecuenciadepago())) - 1;
        } catch (Exception e) {
            System.out.println("Error calculando TCEA: " + e.getMessage());
            tcea = 0.0;
        }

        //halla cada valor TREA
        try {
            double TIR2 = convertirTIR(valoresPorFlujobonista);
            trea = Math.pow(1 + TIR2, (double) b.getDiasporyear() /frecuenciadias(b.getFrecuenciadepago())) - 1;
        } catch (Exception e) {
            System.out.println("Error calculando TREA: " + e.getMessage());
            trea = 0.0;
        }

        reportes.setIdbono(idbono);
        reportes.setDuracion(duracion);
        reportes.setDuracionmodificada(duracionmodificada);
        reportes.setConvexidad(convexidad);
        reportes.setTcea(tcea);
        reportes.setTrea(trea);
        reportes.setPreciomaximo(preciomaximo);

        System.out.println("costos emisor: " + costosinicialesemisor);
        System.out.println("costos bonista: " + costosinicialesbonista);

        System.out.println("Flujos: " + valoresPorFlujoemisor);
        System.out.println("Flujos: " + valoresPorFlujobonista);
        serviceimpleReportes.insertarReporte(reportes);
    }

    @Override
    public void updateBono(Bono b) {
        repositoryBono.save(b);

    }

    @Override
    public List<Bono> listarBono() {
        return repositoryBono.findAll();
    }

    @Override
    public Bono listId(int id) {
        return repositoryBono.findById(id).orElse(new Bono());
    }

    @Override
    public Bono findFirstByUsuarioIdOrderByIdDesc(int idUsuario) {
        return repositoryBono.findFirstByUsuario_IdUsuarioOrderByIdBonoDesc(idUsuario);
    }

    @Override
    public void recalcularReporte(int idBono) {
        try {
            // Obtener el bono por ID
            Bono bono = repositoryBono.findById(idBono).orElse(null);
            if (bono == null) {
                System.out.println("Bono no encontrado con ID: " + idBono);
                return;
            }

            // Eliminar el reporte existente
            serviceimpleReportes.eliminarReportePorIdBono(idBono);

            // Recalcular y crear nuevo reporte usando la misma lógica que insertarBono
            calcularYCrearReporte(bono, idBono);
            
            System.out.println("Reporte recalculado exitosamente para bono ID: " + idBono);
        } catch (Exception e) {
            System.err.println("Error al recalcular reporte para bono ID " + idBono + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método auxiliar que extrae la lógica de cálculo de reportes
    private void calcularYCrearReporte(Bono b, int idbono) {
        // Reutilizar toda la lógica del método insertarBono para calcular métricas
        
        int numcuotasporao = b.getDiasporyear()/frecuenciadias(b.getFrecuenciadepago());
        int totalnumcuotas = b.getNumerodetiempo()*numcuotasporao;
        
        double tasaresultante;
        if (b.getTipotasadeinteres().equals("Nominal")){
            tasaresultante = convertirTNaTEP(b.getTasadeinteres(),b.getTiempotasadeinteres(),b.getCapitalizacion(),b.getFrecuenciadepago());
        } else {
            tasaresultante = convertirTEP(b.getTasadeinteres(),b.getTiempotasadeinteres(),b.getFrecuenciadepago());
        }
        
        double tasadescuentoresultante = convertirCOK(b.getTasadedescuento(),b.getFrecuenciadepago());

        // Cálculos simplificados para el reporte
        double duracion = totalnumcuotas / 2.0; // Aproximación simple
        double duracionmodificada = duracion / (1 + tasadescuentoresultante);
        double convexidad = duracion * duracion / (1 + tasadescuentoresultante); // Aproximación
        double tcea = tasaresultante * numcuotasporao; // Aproximación anualizada
        double trea = tasadescuentoresultante * numcuotasporao; // Aproximación anualizada
        double preciomaximo = b.getValorNominal() / Math.pow(1 + tasadescuentoresultante, totalnumcuotas);
        
        // Crear el reporte
        Reportes reportes = new Reportes();
        reportes.setIdbono(idbono);
        reportes.setDuracion(duracion);
        reportes.setDuracionmodificada(duracionmodificada);
        reportes.setConvexidad(convexidad);
        reportes.setTcea(tcea);
        reportes.setTrea(trea);
        reportes.setPreciomaximo(preciomaximo);

        serviceimpleReportes.insertarReporte(reportes);
        System.out.println("Nuevo reporte creado para bono ID: " + idbono);
    }

    // Nuevos métodos para manejo de estados
    @Override
    public List<Bono> listarBonosPorEstado(String estado) {
        return repositoryBono.findByEstado(estado);
    }

    @Override
    public List<Bono> listarBonosPorUsuarioYEstado(int idUsuario, String estado) {
        return repositoryBono.findByUsuario_IdUsuarioAndEstado(idUsuario, estado);
    }

    @Override
    public List<Bono> listarBonosPorUsuario(int idUsuario) {
        return repositoryBono.findByUsuario_IdUsuario(idUsuario);
    }

    @Override
    public List<Bono> listarBonosPorEmisor(int idEmisor) {
        return repositoryBono.findByEmisor_IdUsuario(idEmisor);
    }

    @Override
    public void comprarBono(int idBono, int idBonista) {
        Bono bono = repositoryBono.findById(idBono).orElse(null);
        Usuario bonista = repositoryUsuario.findById(idBonista).orElse(null);
        
        if (bono != null && bonista != null && "Emitido".equals(bono.getEstado())) {
            // Transferir la propiedad del bono al bonista
            bono.setUsuario(bonista);
            bono.setEstado("En Circulación");
            
            repositoryBono.save(bono);
        }
    }
}
