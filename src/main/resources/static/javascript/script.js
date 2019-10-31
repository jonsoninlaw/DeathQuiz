class AudioController {
   constructor() {
      this.bgMusic = new Audio('Assets/Audio/creepy.mp3');
      this.bgMusic2 = new Audio('Assets/Audio/Spooky_Ride.mp3');
      this.bgMusic3 = new Audio('Assets/Audio/hard.mp3');
      this.flipSound = new Audio('Assets/Audio/flip.wav');
      this.matchSound = new Audio('Assets/Audio/match.wav');
      this.victorySound = new Audio('Assets/Audio/victory.wav');
      this.gameOverSound = new Audio('Assets/Audio/witch.mp3');
      this.bgMusic.volume = 0.5;
      this.bgMusic.loop = true;
   }
   startMusic() {
      this.bgMusic.play();
   }
   stopMusic() {
      this.bgMusic.pause();
      this.bgMusic.currentTime = 0;
   }
   flip() {
      this.flipSound.play();
   }
   match() {
      this.matchSound.play();
   }
   victory() {
      this.stopMusic();
      this.victorySound.play();
   }
   gameOver() {
      this.stopMusic();
      this.gameOverSound.play();
   }
}


class endGame {
   constructor(totalTime) {
       this.totalTime = totalTime;
       this.timeRemaining = totalTime;
       this.timer = document.getElementById('time-remaining')  
       this.audioController = new AudioController();
   }

startGame() {
   this.timeRemaining = this.totalTime;
   this.busy = true;
   setTimeout(() => {
       this.audioController.startMusic();
       this.countdown = this.startCountdown();
       this.busy = true;
   }, 500)
   this.timer.innerText = this.timeRemaining;
}




startCountdown() {
   return setInterval(() => {
       this.timeRemaining--;
       this.timer.innerText = this.timeRemaining;
       if(this.timeRemaining === 0)
           this.gameOver();
   }, 1000);
}

victory() {
   clearInterval(this.countdown);
   this.audioController.victory();
   document.getElementById('victory-text').classList.add('visible');
}

gameOver() {
   clearInterval(this.countdown);
   this.audioController.gameOver();
   document.getElementById('game-over-text').classList.add('visible');
}

}

if (document.readyState == 'loading') {
   document.addEventListener('DOMContentLoaded', ready);
} else {
   ready();
}

function ready() {
   let overlays = Array.from(document.getElementsByClassName('overlay-text'));

   let game = new endGame(50);

   overlays.forEach(overlay => {
       overlay.addEventListener('click', () => {
           overlay.classList.remove('visible');
           game.startGame();
       });
   });
} 