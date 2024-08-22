public class Race {
        private String circuit;
        private String racer;
        private int year;
        private int position;
        private boolean finished;

        public Race(String circuit, String racer, int year, int position, boolean finished){
            this.circuit = circuit;
            this.racer= racer;
            this.year=year;
            this.position=position;
            this.finished=finished;
        }

        public String getCircuit() {
            return circuit;
        }

        public String getRacer() {
            return racer;
        }

        public int getYear() {
            return year;
        }

        public int getPosition() {
            if(getFinished())
                return position;
            else
                return 0;
        }

        public boolean getFinished() {
            return finished;
        }

        public String toString() {
            String result = (circuit + "-" + Integer.toString(year) + "-" + racer + ": " );
            if (finished) {
               result += Integer.toString(position);
            }
            return result;
        }
}

