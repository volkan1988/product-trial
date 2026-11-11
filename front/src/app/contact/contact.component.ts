import {Component, inject} from '@angular/core';
import {CardModule} from "primeng/card";
import {FormBuilder, ReactiveFormsModule, Validators} from "@angular/forms";
import {InputTextModule} from "primeng/inputtext";
import {InputTextareaModule} from "primeng/inputtextarea";
import {Button} from "primeng/button";
import {MessageService} from "primeng/api";
import {ToastModule} from "primeng/toast";

export const MESSAGE_MAX_LENGTH = 300;

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [
    CardModule,
    ReactiveFormsModule,
    InputTextModule,
    InputTextareaModule,
    Button,
    ToastModule
  ],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.css'
})
export class ContactComponent {

  private readonly fb = inject(FormBuilder);
  private readonly messageService = inject(MessageService);

  public readonly contactForm = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    message: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(MESSAGE_MAX_LENGTH)]]
  });

  public submit(): void {
    if (this.contactForm.invalid) {
      this.contactForm.markAllAsTouched();
      return;
    }

    this.messageService.add({
      severity: 'success',
      detail: 'Demande de contact envoyée avec succès.'
    });

  }

  protected readonly MESSAGE_MAX_LENGTH = MESSAGE_MAX_LENGTH;
}
