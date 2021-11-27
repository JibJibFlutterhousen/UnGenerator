public class UnGenerator{
	public static void main(String args[]){
		/*
			create an array of thread objects and initialize their parameters before starting them
		*/
		int num_threads = 1;
		thread_for_Un[] threads = new thread_for_Un[num_threads];
		int start = 15;
		int maxN = 17;
		long start_time = java.lang.System.currentTimeMillis();
		for(int i = 0; i < num_threads; i++){
			threads[i] = new thread_for_Un();
			threads[i].set_start(start + i);
			threads[i].set_maxN(maxN);
			threads[i].set_file_location(String.format("Outputs\\Part_%d.txt", i));
			threads[i].set_num_concurrent_threads(num_threads);
			threads[i].start();
		}
		
		int flag = 0;
		double running_percent_done = 0;
		while(flag < num_threads){
			try{
				/*
					wait for a 10 seconds, then ask each thread if they are done. If the thread is not done, ask for it's percentage of being done and aggregate a total percent done
				*/
				Thread.sleep(10000);
				System.out.printf("Asking the %d threads how done they are%n", num_threads);
				for(int i = 0; i < num_threads; i++){
					if(threads[i].get_done()){
						running_percent_done += 100;
						System.out.printf("  Thread #%d: 100%%  ", i+1);
						flag++;
					}else{
						running_percent_done += threads[i].get_percent_done();
						System.out.printf("  Thread #%d: %.2f%%", i+1, threads[i].get_percent_done());
						flag = 0;
					}
				}
				running_percent_done /= num_threads;
				/*
					print out the total percent done, and if it's 100% done or more, break out of the waiting loop
				*/
				System.out.printf("%nTotal percent: %.2f%%%n", running_percent_done);
				if(running_percent_done >= 100.00){
					break;
				}
				running_percent_done = 0;
			}catch(Exception e){
				System.out.println(e);
			}
		}
		/*
			now aggregate the data by determining the thread that took the longest time to complete it's job
		*/
		System.out.println("\nAggregating data now...\n\n");
		long how_long_it_took = 0;
		for(int i = 0; i < num_threads; i++){
			System.out.printf("Thread %d took %s miliseconds%n", i+1, threads[i].get_time_to_finish());
			if(threads[i].get_time_to_finish() > how_long_it_took){
				how_long_it_took = threads[i].get_time_to_finish();
			}
		}
		System.out.printf("The longest thread took %s miliseconds%n", how_long_it_took);
		long time_to_finish = java.lang.System.currentTimeMillis() - start_time;
		System.out.printf("The program took %s miliseconds%n", time_to_finish);
	}
}