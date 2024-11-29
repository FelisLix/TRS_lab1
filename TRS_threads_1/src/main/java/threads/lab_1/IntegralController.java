package threads.lab_1;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class IntegralController {


    public TextField nThreads;
    public TextField time;
    public TextField n;
    public TextField result;

    public synchronized void send(double v) {
        totalResult += v;
        finished++;
        notify();
    }

    private double totalResult;
    private int finished;

    public void calculateButton(ActionEvent actionEvent) {
        int threads = Integer.parseInt(nThreads.getText());
        int N = Integer.parseInt(n.getText());
        double delta = (double) (1) / threads;
        totalResult = 0;
        finished = 0;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < threads; i++) {
            IntegralCalculator calculator = new IntegralCalculator(  i * delta, i * delta + delta, N / threads, t -> Math.log(t + 1), this);
            new Thread(calculator).start();
        }
        try {
            synchronized (this) {
                while (finished < threads) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted");
        }
        long finishTime = System.currentTimeMillis();
        result.setText(String.valueOf(totalResult));
        long timeRes = finishTime - startTime;
        time.setText(String.valueOf(timeRes));
    }

    public void showTheme(ActionEvent actionEvent) {
        Alert theme = new Alert(Alert.AlertType.INFORMATION);
        theme.setTitle("Лабораторна робота 1");
        theme.setHeaderText("Потоки виконання (threads) і синхронізація");
        theme.show();

    }

    public void showTask(ActionEvent actionEvent) {
        Alert task = new Alert(Alert.AlertType.INFORMATION);
        task.setTitle("Лабораторна робота 1");
        task.setHeaderText("Завдання");
        task.setContentText("""
                Обчислити значення визначеного інтеграла відповідно до варіанту (варіант 4). Реалізацію програми виконувати таким чином:
                1.Створити клас “Функція” (з єдиним методом “обчислити”) для реалізації підінтегральної функції.
                2.Створити клас “Обчислювач інтегралів”, який може працювати у багатопотоковому режимі і має метод “обчислити” з параметрами: a, b - кінці інтервалу, n - кількість кроків та f - підінтегральна функція.
                3.Для цих класів розробити модульні тести і виконати тестування
                4.Створити віконну програму, яка буде дозволяти вводити кількість інтервалів розбиття відрізку інтегрування і кількість потоків виконання.
                5.Як результати роботи програми вивести обчислене значення інтегралу і час, який знадобився для її виконання.
                6.Виконати обчислення декілька разів для різних (від 1 до 20 кількостей потоків виконання) при малій (менше 103 та великій (більше 106 кількості інтервалів розбиття відрізка.
                7.Зробити висновки""");
        task.show();
    }
}